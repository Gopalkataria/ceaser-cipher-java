/**
 * This is s code for encrypting and decrypting stuff using several other things
 * as well each method has a description above it !!
 * 
 * Copyright (c) 2019 [Gopal Kataria]
 */
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

    /**
     * this method encrypts/decrypts text using ceaser cipher method. key for
     * encryption decryption is defined using the {@code key }parameter. {@code
     * negate } bool sets whether the string is for decryption or not, so that the
     * {@code key } is negated
     * 
     * @param message the text to encrypt
     * @param key     key for encryption or decryption. {@code key} parameter
     *                changes automatically according to the {@code negate} boolean
     * @param negate  {@code True} to decrypt text and {@code false } to encrypt
     *                text
     * @return returns an an encrypted or decrypted text using ceaser cipher method
     */

    public static String ceaser_cipher(String message, int key, boolean negate) {

        key = (negate == false) ? key : -(key);
        String[] message_chars = message.split("");
        String new_message = "";
        String alphabets = "euaglvQTOJDXNrishzbWYPHSCMqtodjxnEULGAVwypfkcmRIKFZB";
        String numbers = "4561237890,.:;/?[]{}()$#%^&*";

        for (String chr : message_chars) {

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

    /**
     * shuffles the text in a specific way. EG. 123456 can be made 135246 with key
     * 0. the text can be shuffled in two ways i.e. charactes with odd positions
     * first or vice versa. the text can be rearranged using the
     * {@link #rearrange() } method.
     * 
     * @param message The String to shuffle
     * @param key     Int as key to shuffle
     * @return returns a shuffled string
     */
    public static String shuffle(String message, int key) {

        key = key % 2;

        String new_message = "";

        if (key == 0) {
            for (Integer y = 0; y < message.length(); y += 2) {
                new_message += message.charAt(y);
            }
            for (Integer y = 1; y < message.length(); y += 2) {
                new_message += message.charAt(y);
            }

        }
        if (key == 1) {
            for (Integer y = 1; y < message.length(); y += 2) {
                new_message += message.charAt(y);
            }
            for (Integer y = 0; y < message.length(); y += 2) {
                new_message += message.charAt(y);
            }

        }

        return new_message;

    }

    /**
     * rearranges text shuffled with the {@link #shuffle() } method. reverses the
     * shuffle effect and rearranges text back in original state if correct key in
     * used to depict the sequence
     * 
     * @param message the text to encrypt
     * @param key     the key depicting the order, i.e. charactes with odd positions
     *                were placed first or vice versa
     * @return a rearranged string
     */

    public static String rearrange(String message, int key) {

        key = key % 2;
        String new_message = "", part_1 = "", part_2 = "";
        Integer half = message.length() / 2, len = message.length();

        if (key == 1) {
            part_1 = message.substring(half, len);
            part_2 = message.substring(0, half);
        } else if (key == 0) {
            if (len % 2 == 0) {

                part_1 = message.substring(0, half);
                part_2 = message.substring(half, len);
            } else if (len % 2 == 1) {
                part_1 = message.substring(0, half + 1);
                part_2 = message.substring(half + 1, len);
            }
        }

        for (int i = 0; i < half; i++) {
            new_message += part_1.charAt(i);
            new_message += part_2.charAt(i);
        }

        if (part_1.length() > part_2.length()) {
            new_message += part_1.charAt(part_1.length() - 1);
        }

        if (part_2.length() > part_1.length()) {
            new_message += part_2.charAt(part_2.length() - 1);
        }

        return new_message;
    }

    /**
     * main method for encryption which combines {@link #ceaser_cipher()} and the
     * {@link #shuffle()} funtionality
     * 
     * the text is encrypted using the key. very hard to break encryption
     * 
     * @param message a String message to encrypt
     * @param key     an int key for encryption
     * @return an encrypted string text is returned
     * 
     */

    public static String encrypt(String message, int key) {
        String new_message = ceaser_cipher(message, key, false);
        new_message = shuffle(new_message, key);
        return new_message;
    }

    /**
     * main method for decryption which combines {@link #ceaser_cipher()} and the
     * {@link #rearrange()} funtionality.
     * 
     * the text is decrypted using the key. very hard to break encryption
     * 
     * @param message a String message to encrypt
     * @param key     an int key for encryption
     * @return an encrypted string text
     */

    public static String decrypt(String message, int key) {
        String new_message = ceaser_cipher(message, key, true);
        new_message = rearrange(new_message, key);
        return new_message;
    }
}
