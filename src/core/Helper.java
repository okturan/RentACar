package core;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Helper {
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Metal".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static Boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static Boolean isFieldListEmpty(JTextField... fields) {
        for (JTextField field : fields) {
            if (isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    public static void showMessage(String str) {
        String title;
        String message;

        message = switch (str) {
            case "fill" -> {
                title = "Error";
                yield "Please fill all required fields.";
            }
            case "done" -> {
                title = "Result:";
                yield "Success";
            }

            case "notFound" -> {
                title = "Not Found";
                yield "Record Not Found";
            }
            default -> {
                title = "Attention";
                yield str;
            }
        };
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        String message;
        if (str.equals("sure")) {
            message = "Are you sure?";
        } else {
            message = str;
        }
        return JOptionPane.showConfirmDialog(null, message, "Confirm deletion", JOptionPane.YES_NO_OPTION) ==
                JOptionPane.YES_OPTION;
    }


    public static int getLocation(String type, int size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size) / 2;
            default -> 0;
        };
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat(DATE_PATTERN).format(date);
    }

    public static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat(DATE_PATTERN).parse(dateStr);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
