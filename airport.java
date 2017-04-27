import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;

class airport {
    private static final String STDIN_FILENAME = "-";

    public static treemap load_database(String database_name) {
        treemap tree = new treemap();
        try {
            Scanner database = new Scanner(new File(database_name));
            for(int linenr = 1; database.hasNextLine(); ++linenr) {
                String line = database.nextLine();
                if(line.matches("^\\s*(#.*)?$")) continue;
                String[] keyvalue = line.split(":");
                if(keyvalue.length != 2) {
                 misc.warn (database_name, linenr, "invalid line");
                 continue;
             }
             tree.put(keyvalue[0], keyvalue[1]);
             //loads the values in the tree
         }
         database.close();
     } catch (IOException error) {
        misc.die(error.getMessage());

    }
    return tree ;
    }

public static void main (String[] args) {
    String database = null ;
    boolean do_debug = false;
    
    if(args.length > 2) {
      misc.die("Usage: airport [-d] database");
    }
    else if((args.length ==2 && args[0].compareTo("-d") == 0) && 
        (args[1].charAt(0) != '-')) {
        do_debug = true;
        database = args[1];
    }
    else if((args.length == 1) && (args[0].charAt(0) != '-' )) {
        database = args[0];
    }
    else {
         misc.die("Usage: airport [-d] database");
    }
    //sends the string typed in as an 
    //argument to the load_database function 
    treemap tree = load_database(database);
    Scanner stdin = new Scanner(in);

    //if -d is not specified
    if(!do_debug) {
        while(stdin.hasNextLine()) {
            //accepts values till the user wants to
            String airport = stdin.nextLine().toUpperCase().trim();
            //converts the entered key to Uppercase
            String airport_name = tree.get(airport);
            //calls the function get to check if the key entered
            //exists or not
            if(airport_name == null) {
                err.printf("%s: no such airport%n", airport);
                //prints the message if there is no key found
                exit(1);
            }
            else {
                out.printf("%s airport %n", airport_name);
            }
        }
    } else {
        //if -d option is specified
        tree.debug_tree ();
        exit(misc.exit_status);
    }
}

}