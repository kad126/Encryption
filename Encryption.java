import java.util.Formatter;
import java.util.Scanner;

public class Encryption {
	public final static int UPPER_BOUND = 126;
	public final static int LOWER_BOUND = 32;

	public static void main(String args[]) {
		Scanner data = new Scanner(System.in);
		String input = obtain_text(data);
		int key = obtain_key(data);
		String result = encrypt(input, key);
		// write_to_file(result);
		System.out.println("Encryption " + result);
	}

	public static String encrypt(String input, int key) {
		String result = "";
		for (int i = 0; i < input.length(); i++) {
			char character = input.charAt(i);

			result = result + encrypt_char(character, key);
		}
		return result;
	}

	public static char encrypt_char(char character, int key) {
		int ascii_val = (int) character;
		int ascii_val_and_key = ascii_val + key;

		if (ascii_val_and_key < LOWER_BOUND) {
	
			ascii_val = lower(LOWER_BOUND, UPPER_BOUND, ascii_val_and_key);
			
		} else if (ascii_val_and_key > UPPER_BOUND) {
		
			int remainder = higher(LOWER_BOUND, UPPER_BOUND, ascii_val_and_key);
			System.out.println(remainder);
			ascii_val = ascii_val + remainder;
			if(ascii_val > UPPER_BOUND) {
				int x = ascii_val - UPPER_BOUND;
				ascii_val = (LOWER_BOUND - 1) + x;
			}
		}
		else
			ascii_val = ascii_val_and_key;
		
		return (char) ascii_val;
	}

	public static String obtain_text(Scanner data) {
		data.reset();
		System.out.println("Insert text to encrypt");
		return data.nextLine();
	}

	public static int obtain_key(Scanner data) {
		data.reset();
		System.out.println("What is your encryption key?");
		return data.nextInt();
	}

	// this function will print a given string to a file
	public static void write_to_file(String content) {
		final Formatter x;

		try {
			x = new Formatter("encryption.txt");
			x.format("Encryption: %s", content);
			x.close();
		} catch (Exception e) {
			System.out.println("You got an error");
		}
	}

	// this function loops round from the upper bound back to lower bound
	public static int higher(int lower_bound, int upper_bound, int post_encryption) {
		int difference = upper_bound - lower_bound;
		int remainder = post_encryption % difference;
		return remainder;
	}

	// this function loops round from the lower bound back to the upper bound
	public static int lower(int lower_bound, int upper_bound, int post_encryption) {
		int difference = upper_bound - lower_bound;
		int remainder = post_encryption % difference;
		if (remainder < 0)
			return (upper_bound + 1) + (lower_bound + remainder);
		else
			return (upper_bound + 1) - (lower_bound - remainder);
	}
}
