import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class Encrypter 
{

    private int shift;
    private String encrypted;

    public Encrypter() 
    {
        this.shift = 1;
        this.encrypted = "";
    }

    public Encrypter(int s) 
    {
        this.shift = s;
        this.encrypted = "";
    }

    
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception 
    {
        int n;
    	String input = readFile(inputFilePath);
    	StringBuilder encrypted = new StringBuilder();

        for (char ch : input.toCharArray()) 
        {
        	if(Character.isLetter(ch))
            {
        		int n1 = (int)ch+shift;
        		if(n1 > 90 && Character.isUpperCase(ch))
                {
        			n = n1-90;
        			n1 = 65 + n -1;
        		}
                else if(n1 > 122 && Character.isLowerCase(ch))
                {
        			n = n1-122;
        			n1 = 97 + n - 1;
        		}
                encrypted.append((char)n1);
        	}else
            {
        		encrypted.append(ch);
        	}
        }
        writeFile(encrypted.toString(), encryptedFilePath);
    }

    
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception 
    {
        int d1;
    	String input = readFile(messageFilePath);
    	StringBuilder encrypted = new StringBuilder();

        for (char c1 : input.toCharArray()) 
        {
        	if(Character.isLetter(c1))
            {
        		int n1 = (int)c1-shift;
        		if(n1 < 65 && Character.isUpperCase(c1))
                {
        			d1 = 65-n1;
        			n1 = 90 - d1 + 1;
        		}else if(n1 < 97 && Character.isLowerCase(c1))
                {
        			d1 = 97-n1;
        			n1 = 122 - d1 + 1;
        		}
                encrypted.append((char)n1);
        	}else
            {
        		encrypted.append(c1);
        	}
        }
        writeFile(encrypted.toString(), decryptedFilePath);
    }

    
    private static String readFile(String filePath) throws Exception 
    {
        String message = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)))
        {
        	message = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        return message;
    }

    
    private static void writeFile(String data, String filePath) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) 
        {
            writer.write(data);
            System.out.println("String successfully written to the file: " + filePath);
        } catch (IOException e) 
        {
            System.err.println("Error writing string to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() 
    {
        return encrypted;
    }
}