use std::io;

fn main() {
    /*
    Declaring a variable in Rust using the let keyword:
        -   "let x = 5;" declares a variable named x and assigns it the value of 5. The type of x is inferred to be i32 (32-bit signed integer) by default.
        -   Variable names in Rust are case-sensitive and can contain letters, numbers, and underscores. They must start with a letter or an underscore. The 
            convention in Rust is to use snake_case for variable names, which means that words are separated by underscores and all letters are lowercase.
        -   Variables are immutable by default, which means that once a value is assigned to a variable, it cannot be changed. If we want to make a variable 
            mutable, we can use the mut keyword:
                -   "let mut x = 5;" declares a mutable variable named x and assigns it the value of 5. We can change the value of x later in the code.
        -   Rust is a statically typed language, which means that it must know the types of all variables at compile time. However, we can also explicitly 
            specify the type of a variable:
                -   "let x: i32 = 5;" declares a variable named x of type i32 and assigns it the value of 5.
        -   Rust has a strong type system, which means that it enforces strict rules about how values of different types can be used together. For example, 
            we cannot add a string and an integer together without explicitly converting one of the values to the other type.
        -   Variables in Rust have a scope, which is the region of the code where the variable is valid. A variable is valid from the point it is declared 
            until the end of the block in which it is declared. For example, if we declare a variable inside a function, it will only be valid within that 
            function.  
        -   Rust has a concept of shadowing, which allows us to declare a new variable with the same name as a previous immutable variable. The new variable 
            will shadow the previous variable, and the previous variable will no longer be accessible. For example:
                -   "let x = 5;" declares a variable named x and assigns it the value of 5.
                -   "let x = x + 1;" declares a new variable named x that shadows the previous variable and assigns it the value of 6.
                -   "let x = x * 2;" declares a new variable named x that shadows the previous variable and assigns it the value of 12.
        -   Rust has a concept of ownership, which is a set of rules that govern how memory is managed in Rust. Each value in Rust has a single owner, and when the 
            owner goes out of scope, the value is dropped and the memory is freed. This helps prevent memory leaks and other memory-related bugs.
        -   Rust has a concept of borrowing, which allows us to temporarily use a value without taking ownership of it. We can borrow a value by using a reference, 
            which is denoted by the & symbol. For example:
                -   "let x = 5;" declares a variable named x and assigns it the value of 5.
                -   "let y = &x;" declares a variable named y that borrows the value of x. We can use y to access the value of x, but we cannot modify it.
        -   Rust has a concept of lifetimes, which are a way to specify how long references are valid. Lifetimes help prevent dangling references and other 
            memory-related bugs.
        -   Rust has a concept of type inference, which allows the compiler to automatically determine the type of a variable based on its value. This can make code 
            more concise and easier to read.
        -   Rust has a concept of pattern matching, which allows us to match values against patterns and execute code based on the match. Pattern matching can be used 
            with variables, enums, and other types.
        
        -   Rust has a concept of constants, which are values that cannot be changed and are valid for the entire duration of the program. Constants are declared using the 
            const keyword:
                -   "const MAX_POINTS: u32 = 100_000;" declares a constant named MAX_POINTS of type u32 and assigns it the value of 100,000. The type of a constant must 
                    be explicitly specified, and it must be a compile-time constant. 
                -   "static mut MAX_POINTS: u32 = 100_000;" declares a static mutable variable named MAX_POINTS of type u32 and assigns it the value of 100,000. The static 
                    keyword allows the variable to be valid for the entire duration of the program and can be mutable.
            
    We show variables on windows as:
        -   "println!("The value of x is: {}", x);" prints the value of x to the console. The {} is a placeholder for the value of x, which is passed as an argument 
        to the println! macro.
        -   "{}" is a placeholder for a value that will be printed to the console. We can use multiple placeholders in a single string, and we can specify the order of the
        placeholders using numbers inside the curly braces. For example:
            -   "println!("The value of x is: {0}, and the value of y is: {1}", x, y);" prints the value of x and y to the console. The {0} and {1} are placeholders for 
                the values of x and y, respectively
    
    Operations:
        -   "let sum = x + 5;" adds 5 to the value of x and assigns the result to a new variable named sum.
        -   "let difference = x - 5;" subtracts 5 from the value of x and assigns the result to a new variable named difference.
        -   "let product = x * 5;" multiplies the value of x by 5 and assigns the result to a new variable named product.
        -   "let quotient = x / 5;" divides the value of x by 5 and assigns the result to a new variable named quotient.
        -   "let remainder = x % 5;" calculates the remainder of dividing the value of x by 5 and assigns the result to a new variable named remainder.
        -   We can add new operations to the program by using the appropriate operator. For example, we can add a new operation to calculate the square of x:
            -   "let square = x * x;" calculates the square of x and assigns the result to a new variable named square.
        -   There exist math libraries in Rust that provide additional mathematical functions and constants. For example, we can use the std::f64::consts module to 
            access mathematical constants such as pi and e:
            -   "let pi = std::f64::consts::PI;" assigns the value of pi to a new variable named pi.
            -   "let e = std::f64::consts::E;" assigns the value of e to a new variable named e.
        -   We can also add a library to the program by using the appropriate use statement. For example, we can add the rand crate to the program to generate random numbers
            -   "use rand::Rng;" imports the Rng trait from the rand crate, which provides methods for generating random numbers.
            -   "let mut rng = rand::thread_rng();" creates a new random number generator using the thread_rng function, which returns a random number generator that is local to the current thread.
            -   "let random_number = rng.gen_range(1..=10);" generates a random number between 1 and 10 (inclusive) using the gen_range method of the Rng trait and assigns it 
                to a new variable named random_number.
        -   We can also use the rand crate to generate random numbers of different types, such as floating-point numbers and booleans. For example:
            -   "let random_float = rng.gen_range(0.0..1.0);" generates a random floating-point number between 0.0 and 1.0 (exclusive) using the gen_range method of the Rng trait and assigns it to a new variable named random_float.
            -   "let random_bool = rng.gen_bool(0.5);" generates a random boolean value with a 50% chance of being true or false using the gen_bool method of the Rng trait and assigns it to a new variable named random_bool.
        
        -   Mathematical operators in Rust are used to perform arithmetic operations on numeric values. The following are the mathematical operators in Rust:
            -   "+" adds two values together.
            -   "-" subtracts one value from another.
            -   "*" multiplies two values together.
            -   "/" divides one value by another.
            -   "%" calculates the remainder of dividing one value by another.
        -   Relational operators in Rust are used to compare values and return a boolean result. The following are the relational operators in Rust:
            -   "==" checks if two values are equal.
            -   "!=" checks if two values are not equal.
            -   ">" checks if the left value is greater than the right value.
            -   "<" checks if the left value is less than the right value.
            -   ">=" checks if the left value is greater than or equal to the right value.
            -   "<=" checks if the left value is less than or equal to the right value.
        -   Logical operators in Rust are used to combine boolean values and return a boolean result. The following are the logical operators in Rust:
            -   "&&" returns true if both values are true.
            -   "||" returns true if at least one value is true.
            -   "!" returns true if the value is false, and false if the value is true.
        -  Bitwise operators in Rust are used to perform operations on the individual bits of integer values. The following are the bitwise operators in Rust:
            -   "&" performs a bitwise AND operation.
            -   "|" performs a bitwise OR operation.
            -   "^" performs a bitwise XOR operation.
            -   "<<" performs a left shift operation.
            -   ">>" performs a right shift operation.
        -   Assignment operators in Rust are used to assign values to variables. The following are the assignment operators in Rust:
            -   "=" assigns a value to a variable.
            -   "+=" adds a value to a variable and assigns the result to the variable.
            -   "-=" subtracts a value from a variable and assigns the result to the variable.
            -   "*=" multiplies a variable by a value and assigns the result to the variable.
            -   "/=" divides a variable by a value and assigns the result to the variable.
            -   "%=" calculates the remainder of dividing a variable by a value and assigns the result to the variable.
        

    Data types:
        -   Rust has a number of built-in data types, including integers, floating-point numbers, booleans, and characters. We can also define our own data types using 
            structs and enums.
        -   Rust has a concept of type casting, which allows us to convert a value from one type to another. We can use the as keyword to perform type casting. For example:
            -   "let x = 5;" declares a variable named x and assigns it the value of 5.
            -   "let y = x as f64;" declares a variable named y that is a floating-point number and assigns it the value of x converted to f64.
            -   "let z = y as i32;" declares a variable named z that is an integer and assigns it the value of y converted to i32.
        -   Rust has a concept of type inference, which allows the compiler to automatically determine the type of a variable based on its value. This can make code 
            more concise and easier to read.
        -   We can change how many bits are used to store a number by specifying the type of the variable. For example, we can use i8 to store an 8-bit signed integer, u16 
            to store a 16-bit unsigned integer, and f32 to store a 32-bit floating-point number. 
        -   All bits distribution: 8, 16, 32, 64, 128. The default is 32 bits. The default type for integers is i32, and the default type for floating-point numbers is f64.
        -   All types: 
            -   i: signed integers
            -   u: unsigned integers
            -   f: floating-point numbers
            -   bool: boolean values
            -   char: character values
            -   str: string values
            -   array: fixed-size arrays
            -   tuple: fixed-size lists with potentially different types
            -   slice: dynamic arrays
            -   reference: references to values
            -   pointer: pointers to values
            -   struct: custom data structures
            -   enum: custom data types with multiple variants
            -   union: custom data types that can hold values of different types
            -   function pointer: pointers to functions
            -   closure: anonymous functions that can capture their environment
            -   str: string slices/ string: literals

    */

    let x = 5;
    let sum = x + 5;
    let difference = x - 5;
    let product = x * 5;
    let quotient = x / 5;
    let remainder = x % 5;
    println!("The value of x is: {}", x);
    println!("The sum of x and 5 is: {}", sum);
    println!("The difference of x and 5 is: {}", difference);
    println!("The product of x and 5 is: {}", product);
    println!("The quotient of x and 5 is: {}", quotient);
    println!("The remainder of x and 5 is: {}", remainder); 
    
    /*
    To read input from the user, we can use the std::io module, which provides functions for reading input from the console. We can use the read_line function 
    to read a line of input from the user and store it in a variable. For example:
        -   "use std::io;" imports the std::io module, which provides functions for reading input from the console.
        -   "let mut input = String::new();" declares a mutable variable named input and assigns it an empty string. We will use this variable to store the user's input.
        -   "io::stdin().read_line(&mut input).expect("Failed to read line");" reads a line of input from the user and stores it in the input variable. The &mut input syntax 
            indicates that we are passing a mutable reference to the input variable. The expect function is used to handle any errors that may occur while reading input.
        -   "let trimmed_input = input.trim();" removes any leading or trailing whitespace from the user's input and stores it in a new variable named trimmed_input.
        -   "println!("You entered: {}", trimmed_input);" prints the user's input to the console.

    */

    let mut input = String::new();
    std::io::stdin().read_line(&mut input).expect("Failed to read line");
    let trimmed_input = input.trim();
    println!("You entered: {}", trimmed_input);

}
