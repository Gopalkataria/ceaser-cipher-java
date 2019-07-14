
public class crypto {
    public static void main(String[] args) {
        String command = args[0];
        if (command.equals("encrypt")) {
            String text = args[1];
            int key = Integer.parseInt(args[2]);
            String out = encrypt(text, key);
            System.out.println("\n \t" + out);
        } else if (command.equals("decrypt")) {
            String text = args[1];
            int key = Integer.parseInt(args[2]);
            String out = decrypt(text, key);
            System.out.println("\n \t" + out);
        } else {
            System.out.println("\n\n    Invalid Input");
            System.out.println("\n    Accepted args ");
            System.out.println(
                    "\n \t [action] [text] [key] \n \n \t action \t encrypt or decrypt \n \t text \t text to encrypt or decrypt \n \t key \t key integer for decryption ");
        }

    }

    public static String ceaser_cipher(String msg, int key, boolean negate) {

        key = (negate == false) ? key : -(key);
        String[] message = msg.split("");
        String new_message = "";
        String alphabets = "euaglvQTOJDXNrishzbWYPHSCMqtodjxnEULGAVwypfkcmRIKFZB";
        String numbers = "4561237890";

        for (String chr : message) {
            if (alphabets.contains(chr)) {
                Integer position = (alphabets.indexOf(chr) + key) % alphabets.length();

                position = (position < 0) ? alphabets.length() + position : position;

                new_message += alphabets.charAt(position);

            } else if (numbers.contains(chr)) {
                Integer position = (numbers.indexOf(chr) + key) % numbers.length();

                position = (position < 0) ? numbers.length() + position : position;

                new_message += numbers.charAt(position);

            } else {
                new_message += chr;
            }

        }

        return new_message;

    }

    public static String encrypt(String message, int key) {
        String new_message = ceaser_cipher(message, key, false);
        return new_message;
    }

    public static String decrypt(String message, int key) {
        String new_message = ceaser_cipher(message, key, true);
        return new_message;
    }
}