PGDMP  1    5                |            rentacar    16.3 (Postgres.app)    16.3 *    K           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            L           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            M           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            N           1262    16390    rentacar    DATABASE     t   CREATE DATABASE rentacar WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE rentacar;
                postgres    false            �            1259    16392    app_user    TABLE     �   CREATE TABLE public.app_user (
    user_id integer NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    role text NOT NULL
);
    DROP TABLE public.app_user;
       public         heap    postgres    false            �            1259    16391    app_user_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.app_user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.app_user_user_id_seq;
       public          postgres    false    216            O           0    0    app_user_user_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.app_user_user_id_seq OWNED BY public.app_user.user_id;
          public          postgres    false    215            �            1259    16442    booking    TABLE     �  CREATE TABLE public.booking (
    booking_id integer NOT NULL,
    car_id integer NOT NULL,
    customer_name text NOT NULL,
    customer_id_no text NOT NULL,
    customer_mobile_no text NOT NULL,
    customer_email text NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    booking_case text,
    notes text,
    price integer NOT NULL,
    CONSTRAINT chk_dates CHECK ((end_date > start_date))
);
    DROP TABLE public.booking;
       public         heap    postgres    false            �            1259    16441    booking_booking_id_seq    SEQUENCE     �   CREATE SEQUENCE public.booking_booking_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.booking_booking_id_seq;
       public          postgres    false    224            P           0    0    booking_booking_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.booking_booking_id_seq OWNED BY public.booking.booking_id;
          public          postgres    false    223            �            1259    16401    brand    TABLE     V   CREATE TABLE public.brand (
    brand_id smallint NOT NULL,
    name text NOT NULL
);
    DROP TABLE public.brand;
       public         heap    postgres    false            �            1259    16400    brand_brand_id_seq    SEQUENCE     �   CREATE SEQUENCE public.brand_brand_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.brand_brand_id_seq;
       public          postgres    false    218            Q           0    0    brand_brand_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.brand_brand_id_seq OWNED BY public.brand.brand_id;
          public          postgres    false    217            �            1259    16419    car    TABLE     �   CREATE TABLE public.car (
    car_id integer NOT NULL,
    model_id integer NOT NULL,
    color text NOT NULL,
    km integer NOT NULL,
    plate text NOT NULL
);
    DROP TABLE public.car;
       public         heap    postgres    false            �            1259    16418    car_car_id_seq    SEQUENCE     �   CREATE SEQUENCE public.car_car_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.car_car_id_seq;
       public          postgres    false    222            R           0    0    car_car_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.car_car_id_seq OWNED BY public.car.car_id;
          public          postgres    false    221            �            1259    16410    model    TABLE     �   CREATE TABLE public.model (
    model_id smallint NOT NULL,
    brand_id integer NOT NULL,
    name text NOT NULL,
    year text NOT NULL,
    transmission_type text NOT NULL,
    fuel_type text NOT NULL,
    vehicle_type text NOT NULL
);
    DROP TABLE public.model;
       public         heap    postgres    false            �            1259    16409    model_model_id_seq    SEQUENCE     �   CREATE SEQUENCE public.model_model_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.model_model_id_seq;
       public          postgres    false    220            S           0    0    model_model_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.model_model_id_seq OWNED BY public.model.model_id;
          public          postgres    false    219            �           2604    16460    app_user user_id    DEFAULT     t   ALTER TABLE ONLY public.app_user ALTER COLUMN user_id SET DEFAULT nextval('public.app_user_user_id_seq'::regclass);
 ?   ALTER TABLE public.app_user ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    16445    booking booking_id    DEFAULT     x   ALTER TABLE ONLY public.booking ALTER COLUMN booking_id SET DEFAULT nextval('public.booking_booking_id_seq'::regclass);
 A   ALTER TABLE public.booking ALTER COLUMN booking_id DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    16428    brand brand_id    DEFAULT     p   ALTER TABLE ONLY public.brand ALTER COLUMN brand_id SET DEFAULT nextval('public.brand_brand_id_seq'::regclass);
 =   ALTER TABLE public.brand ALTER COLUMN brand_id DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    16422 
   car car_id    DEFAULT     h   ALTER TABLE ONLY public.car ALTER COLUMN car_id SET DEFAULT nextval('public.car_car_id_seq'::regclass);
 9   ALTER TABLE public.car ALTER COLUMN car_id DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    16413    model model_id    DEFAULT     p   ALTER TABLE ONLY public.model ALTER COLUMN model_id SET DEFAULT nextval('public.model_model_id_seq'::regclass);
 =   ALTER TABLE public.model ALTER COLUMN model_id DROP DEFAULT;
       public          postgres    false    220    219    220            @          0    16392    app_user 
   TABLE DATA           E   COPY public.app_user (user_id, username, password, role) FROM stdin;
    public          postgres    false    216   w.       H          0    16442    booking 
   TABLE DATA           �   COPY public.booking (booking_id, car_id, customer_name, customer_id_no, customer_mobile_no, customer_email, start_date, end_date, booking_case, notes, price) FROM stdin;
    public          postgres    false    224   �.       B          0    16401    brand 
   TABLE DATA           /   COPY public.brand (brand_id, name) FROM stdin;
    public          postgres    false    218   D1       F          0    16419    car 
   TABLE DATA           A   COPY public.car (car_id, model_id, color, km, plate) FROM stdin;
    public          postgres    false    222   �1       D          0    16410    model 
   TABLE DATA           k   COPY public.model (model_id, brand_id, name, year, transmission_type, fuel_type, vehicle_type) FROM stdin;
    public          postgres    false    220   3       T           0    0    app_user_user_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.app_user_user_id_seq', 10, true);
          public          postgres    false    215            U           0    0    booking_booking_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.booking_booking_id_seq', 32, true);
          public          postgres    false    223            V           0    0    brand_brand_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.brand_brand_id_seq', 25, true);
          public          postgres    false    217            W           0    0    car_car_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.car_car_id_seq', 23, true);
          public          postgres    false    221            X           0    0    model_model_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.model_model_id_seq', 25, true);
          public          postgres    false    219            �           2606    16449    booking booking_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (booking_id);
 >   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_pkey;
       public            postgres    false    224            �           2606    16408    brand brand_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (brand_id);
 :   ALTER TABLE ONLY public.brand DROP CONSTRAINT brand_pkey;
       public            postgres    false    218            �           2606    16426    car car_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (car_id);
 6   ALTER TABLE ONLY public.car DROP CONSTRAINT car_pkey;
       public            postgres    false    222            �           2606    16417    model model_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_pkey PRIMARY KEY (model_id);
 :   ALTER TABLE ONLY public.model DROP CONSTRAINT model_pkey;
       public            postgres    false    220            �           2606    16399    app_user user_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 <   ALTER TABLE ONLY public.app_user DROP CONSTRAINT user_pkey;
       public            postgres    false    216            �           2606    16450    booking fk_booking_car    FK CONSTRAINT     v   ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fk_booking_car FOREIGN KEY (car_id) REFERENCES public.car(car_id);
 @   ALTER TABLE ONLY public.booking DROP CONSTRAINT fk_booking_car;
       public          postgres    false    224    3498    222            �           2606    16436    car fk_car_model    FK CONSTRAINT     v   ALTER TABLE ONLY public.car
    ADD CONSTRAINT fk_car_model FOREIGN KEY (model_id) REFERENCES public.model(model_id);
 :   ALTER TABLE ONLY public.car DROP CONSTRAINT fk_car_model;
       public          postgres    false    222    220    3496            �           2606    16455    model fk_model_brand    FK CONSTRAINT     z   ALTER TABLE ONLY public.model
    ADD CONSTRAINT fk_model_brand FOREIGN KEY (brand_id) REFERENCES public.brand(brand_id);
 >   ALTER TABLE ONLY public.model DROP CONSTRAINT fk_model_brand;
       public          postgres    false    3494    220    218            @   )   x�3�LL����442����8Ssr�+SS��0W� F�T      H   �  x�}T�n�0<���?��HQ�5�Ӽ4H���-31c�4$�m��ݕ�% /��fv�%�pٺʳo���2�&�dY��6���L]^{���qͮ��J(��)N�=�J�c�B��XH	x�;��5�L�����)Y2�RH)3�?`�@���p��o}�<�%x�B��u�ُ*�=�B��F���B�� �#��<j*xt��C�p�ߘ��S�b�־ۂ-����]�Z���uH�����ƚ��D ��yRs,����.D�up��R�;d<���=$� <��f{�D#q`��\�Y�s;lsO�y>;���y#J`���|���Iʃ�V��jk� |5BfIp���?'��&#X��U�}��
�ں��ݹ�G��%EY��REQ����ěh>�SZ��c;-*�A��Ƶ���:��{�(���a	�A��4�,&f�ip:�.:���S(%%�
-W�[��[lϋ�S��t�K!�ɀ�b! ϕ����)�%�@��e��6�b{�����|61����޳��h����$��������NeS9���Ю�j�%(�qW�	������C��m�Q{��L*�'�Y	n�&�e��P*za�!���M�N�o���kv�}���;zCP�px�~��b��8�      B   x   x�M��
�@D뙏����(5 B�.`csq/�\�$B�z7���猠Ks�ה���wP�м�So#�l�Th�r�����"�P�%��Z��l蕅��������߳s��ؐ�ڔ&�      F   ;  x�5�KS1��3?��$�<�,�Ey���Z�����v�R5��lݳ�4��kM�(�����6d �߾H$�U�1*�l�����SS������qA͏���L�'>DŎU����z�<��

�'y��D<G�3G��Z��cQ��Am2/�0� ��I��U�jCrO��f0!�g���h	i1�L�6��h���l�"��#LSM���
x�y�d�M�z�����ߏ�f�*�_D�d�v���Z�B�`֥E���̰��O2_,g�xR�M�Vن�e-�;�{�������'a�3�������_��oJl�      D   a  x���Qo�0��/��?�B�<v�*�²��tJ�*d���.�,�	������sn	�j_}��,K71KC+&7Q��b���\Y�Z+$I 1K2���>���m�o�Ga��-�ĳ[hޙ�������^|[�1Fΐc�mY�Z��1������)闛#+]�7#��A\�r=����O���Bhr"���K,���"�Ꙋ�\�>�)ת�G,08wl�����> �:�*5�� �T�e�u+ �A�U~8��AO�d��v�����|}�������0��ס)6���H�h��6͢]�f	3�N�5hwo䨋�n]����ov��]=L��W��β�_��     