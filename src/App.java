import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static ArrayList<String> Array_From_File(String file_directory) {
        ArrayList<String> Array = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file_directory))){
            String file_line;

            //use != instead of == or like .equals because if you used that the condition would check before you even execute the loop
            while ((file_line = reader.readLine()) != null) {
                Array.add(file_line);
            }

        } catch (IOException e) {
            System.out.println("error in reading file");
        }

        return Array;
    }

    public static void Write_Array_Count(ArrayList<String> OG_File_Array, String file_directory) {
        try {
            FileWriter writer = new FileWriter(file_directory);

            while((OG_File_Array.size()) != 0 ) {
                String target_word = OG_File_Array.get(0);
                int word_count = 0;
                for(int index = 0; index < OG_File_Array.size(); ) {

                    String word = OG_File_Array.get(index);

                    if(word.toLowerCase().equals(target_word.toLowerCase())){
                        word_count++;
                        OG_File_Array.remove(index);
                    } else {
                        index++;
                    }
    
                }
                writer.write(target_word.toLowerCase() + " " + word_count + "\n");
            }
            writer.close();  
        } catch(IOException e) {

        }
    }

    public static void main(String[] args) throws Exception {
        String response;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Hello, this program will take an inputted .txt file and output a text file that counts the number of each word in the file");
            
            
            String file_name;
            String folder_directory;

            //grabs folder of the input text file
            while (true){
                //check if folder is valid
                while(true){
                    //receiving file directory and file name to create file path
                    System.out.println("please enter the directory of the folder where your text file of words is stored");
                    folder_directory = scanner.nextLine();

                    //attempt to open folder to see if its valid
                    File folder = new File(folder_directory);
                    if (!folder.isDirectory()){
                        System.out.println("EROR: Please enter a valid folder directory");
                        continue;
                    }
                    break;
                }

                //receiving file directory
                while(true){
                    System.out.println("please enter the name of the file (include the .txt extension)");
                    file_name = scanner.nextLine();

                    //attempt to open file to see if its valid
                    File text_file = new File(folder_directory + File.separator + file_name);
                    if (!text_file.exists()) {
                        System.out.println("EROR: Please enter a valid file");
                        continue;
                    }
                    break;
                } 
                break;
            }

            ArrayList<String> Inputted_Array = Array_From_File(folder_directory + File.separator + file_name);
            String File_Folder_and_Name = folder_directory + File.separator + "counted_words";
            Write_Array_Count(Inputted_Array, File_Folder_and_Name);
            System.out.println("Your file has been printed");



            while(true) {
                System.out.println("Type in \"continue\" to restart the program or type in \"exit\" to close the program");
                response = scanner.nextLine();
                switch(response){
                    case "exit":
                        scanner.close();
                        System.exit(0);
                    case "continue":
                        break;
                    default:
                        System.out.println("Please enter a valid key word");
                        continue;
                }
                break;
            }
        }
    }
}
