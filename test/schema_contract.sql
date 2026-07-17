DO $$
DECLARE
    actual_tables text[];
BEGIN
    SELECT array_agg(table_name ORDER BY table_name)
      INTO actual_tables
      FROM information_schema.tables
     WHERE table_schema = 'public'
       AND table_type = 'BASE TABLE';

    IF actual_tables IS DISTINCT FROM ARRAY[
        'app_user',
        'booking',
        'brand',
        'car',
        'model'
    ]::text[] THEN
        RAISE EXCEPTION 'Unexpected public tables: %', actual_tables;
    END IF;
END
$$;

DO $$
DECLARE
    missing_constraints text[];
BEGIN
    SELECT array_agg(expected.name ORDER BY expected.name)
      INTO missing_constraints
      FROM (
          VALUES
              ('booking', 'booking_pkey', 'p'),
              ('booking', 'chk_dates', 'c'),
              ('booking', 'fk_booking_car', 'f'),
              ('brand', 'brand_pkey', 'p'),
              ('car', 'car_pkey', 'p'),
              ('car', 'fk_car_model', 'f'),
              ('model', 'fk_model_brand', 'f'),
              ('model', 'model_pkey', 'p'),
              ('app_user', 'user_pkey', 'p')
      ) AS expected(table_name, name, kind)
     WHERE NOT EXISTS (
         SELECT 1
           FROM pg_constraint constraint_record
           JOIN pg_class table_record
             ON table_record.oid = constraint_record.conrelid
           JOIN pg_namespace namespace_record
             ON namespace_record.oid = table_record.relnamespace
          WHERE namespace_record.nspname = 'public'
            AND table_record.relname = expected.table_name
            AND constraint_record.conname = expected.name
            AND constraint_record.contype = expected.kind::"char"
     );

    IF missing_constraints IS NOT NULL THEN
        RAISE EXCEPTION 'Missing data-model constraints: %', missing_constraints;
    END IF;
END
$$;

SELECT 'RentACar sample archive contract verified.' AS result;
