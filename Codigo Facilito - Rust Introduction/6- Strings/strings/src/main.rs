fn main() {
    /*
    Strings in Rust are a sequence of characters. Rust provides two main types of strings: String and &str. 
    The String type is a growable, heap-allocated string, while the &str type is a string slice that represents 
    a view into a string. Strings in Rust are UTF-8 encoded, which means that they can represent any valid Unicode 
    character.

    Definition:
        -   String: The String type is defined using the String struct. It is a growable, heap-allocated string that can be modified at runtime. 
            We can create a new String using the String::new() method or by converting a string literal into a String using the to_string() method. 
            For example, we can create a new String as follows:
                -   "let mut my_string = String::new();"
                -   "let my_string = "Hello, world!".to_string();"
        -   &str: The &str type is defined using the str primitive type. It is a string slice that represents a view into a string. 
            We can create a new &str by using a string literal or by taking a slice of an existing String. For example, we can create a new &str as follows:
                -   "let my_str = "Hello, world!";"
                -   "let my_str = &my_string[0..5];"
    Ways to define:
        -   let my_string = String::new(); // An empty String
        -   let my_string = "Hello, world!".to_string(); // A String created from a string literal
        -   let my_str = "Hello, world!"; // A string slice created from a string literal
        -   let my_str = &my_string[0..5]; // A string slice created from an existing String
    Ways to access:
        -   let first_char = my_string.chars().nth(0); // Accessing the first character of a String using the chars() method
        -   let first_char = my_str.chars().nth(0); // Accessing the first character of a string slice using the chars() method
        -   let length = my_string.len(); // Getting the length of a String using the len() method
        -   let length = my_str.len(); // Getting the length of a string slice using the len() method
    Ways to modify:
        -   my_string.push('!'); // Adding a character to the end of a String
        -   my_string.push_str(" How are you?"); // Adding a string slice to the end of a String
        -   my_string.insert(5, ','); // Inserting a character at a specific index in a String
        -   my_string.remove(5); // Removing a character at a specific index in a String
        -   my_string.clear(); // Clearing all the characters from a String
    Ways to remove:
        -   my_string.pop(); // Removing the last character from a String
    Strings in Rust are implemented using the standard library, which provides a variety of methods for manipulating and accessing the characters in a string. The String type is mutable, which means that we can modify
    the contents of a String at runtime. The &str type is immutable, which means that we cannot modify the contents of a string slice. However, we can create a new string slice that represents a different view into the same string.

    Interesting methods:
        -   to_uppercase(): Converts all the characters in a String or &str to uppercase.
        -   to_lowercase(): Converts all the characters in a String or &str to lowercase.
        -   trim(): Removes whitespace from the beginning and end of a String or &str.
        -   replace(): Replaces all occurrences of a substring in a String or &str with another substring.
        -   split(): Splits a String or &str into an iterator of substrings based on a delimiter.
        -   contains(): Checks if a String or &str contains a specific substring.
        -   starts_with(): Checks if a String or &str starts with a specific substring.
        -   ends_with(): Checks if a String or &str ends with a specific substring.
        -   .to_string(): Converts a &str to a String.
    Operators:
        -   +: Concatenates two Strings or a String and a &str.
        -   +=: Appends a &str to a String.
        -   ==: Compares two Strings or a String and a &str for equality.
        -   !=: Compares two Strings or a String and a &str for inequality.
        -   <, >, <=, >=: Compares two Strings or a String and a &str lexicographically.


    */

    // Example: create a String
    let mut my_string = String::new();
    my_string.push_str("Hello");
    println!("String example: {}", my_string);

    // Example: create a &str
    let my_str = "Hello, world!";
    println!("String slice example: {}", my_str);

    // Example: access characters
    let first_char = my_str.chars().nth(0);
    println!("First character: {:?}", first_char);

    // Example: length
    println!("Length: {}", my_string.len());

    // Example: modify a String
    my_string.push('!');
    my_string.push_str(" Rust");
    println!("Modified String: {}", my_string);

    // Example: remove from a String
    my_string.pop();
    println!("After pop: {}", my_string);

    // Example: uppercase/lowercase/trim/replace/split/contains
    let phrase = "  Rust programming is fun  ";
    println!("Uppercase: {}", phrase.to_uppercase());
    println!("Lowercase: {}", phrase.to_lowercase());
    println!("Trimmed: {}", phrase.trim());
    println!("Replaced: {}", phrase.replace("fun", "awesome"));
    println!("Contains 'programming'? {}", phrase.contains("programming"));

    // Example: concatenate strings
    let greeting = String::from("Hello");
    let name = "Rust";
    let combined = greeting + " " + name;
    println!("Concatenated: {}", combined);
}
