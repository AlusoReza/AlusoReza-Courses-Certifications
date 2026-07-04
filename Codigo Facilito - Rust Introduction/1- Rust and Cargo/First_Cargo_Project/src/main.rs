// Rust extension is .rs
// Comments in Rust are written with two slashes (//) for single-line comments or with /* */ for multi-line comments.

/*
    Rust is a systems programming language that runs blazingly fast, prevents segfaults, and guarantees thread safety. 
    It is designed to be safe, concurrent, and practical. Rust is syntactically similar to C++, but it provides memory 
    safety without using garbage collection.

    We work using functions primarily, and we can define our own functions. Functions are defined using the fn keyword, 
    followed by the function name.

    Rust is a statically typed language, which means that it must know the types of all variables at compile time.
    Rust is a compiled language, which means that it is converted into machine code that the computer can execute directly.
    To compile a Rust program, we use the rustc command followed by the name of the source file. For example, to compile a 
    Rust program named hello.rs, we would run the following command in the terminal:
        -   "rustc hello.rs"
    After compiling, we can run the resulting executable file to see the output of our program. The name of the executable
    file will be the same as the source file, but without the .rs extension. For example, if we compiled hello.rs, the resulting 
    executable file would be named hello. We can run the executable file by typing its name in the terminal and pressing enter. 
    For example, to run the hello executable, we would type: 
        -   "./hello".
    If there exists a graphical user interface (GUI) for the operating system, we can also run the executable file by double-clicking 
    on it in the file explorer.

    Cargo is the Rust package manager and build system. It is used to manage Rust projects, including building, testing, and
    packaging code. Cargo makes it easy to manage dependencies, compile code, and run tests. It is the recommended way to build and manage 
    Rust projects, and it is included with the Rust installation. If you are working in a big project, it is recommended to use Cargo 
    to manage your project. Cargo uses a file called Cargo.toml to manage dependencies and project settings.
    We can create a new Rust project using Cargo by running the following command in the terminal:
        -   "cargo new project_name"
    The new file will include a Cargo.toml file and a src directory with a main.rs file. The Cargo.toml file is used to manage dependencies 
    and project settings, while the main.rs file is where we write our Rust code.
    Using Cargo permits to compile and run the project with a single command. To compile and run a Rust project using Cargo, we can use
    the following command in the terminal:
        -   "Cargo build" compiles the project and creates an executable file in the target/debug directory.
        -   "Cargo run" compiles the project (if necessary) and runs the resulting executable file.
    After compile the target/debug directory will contain the executable file with the same name as the project. We can run the executable 
    file by typing its name in the terminal and pressing enter. For example, to run a project named my_project, we would type:
        -   "./target/debug/my_project"

    fn main() is the entry point of a Rust program. The main function is special in that it is the first code that runs 
    in every executable Rust program.

    Stack: 
        -   The stack is a region of memory that stores data in a last-in, first-out (LIFO) manner. It is used for 
            storing local variables and function call information. The stack has a fixed size, and when it is full, 
            the program will crash with a stack overflow error. The stack is very fast because it is managed by the 
            CPU and does not require any additional memory allocation or deallocation.
    Heap:
        -   The heap is a region of memory that is used for dynamic memory allocation. It is used for storing data that can change in 
            size or lifetime, such as objects and data structures. The heap has a variable size, and when it is full, the program will 
            crash with an out-of-memory error. The heap is slower than the stack because it requires additional memory allocation and 
            deallocation, which can be time-consuming.
    Ownership:
        -   Ownership is a set of rules that govern how memory is managed in Rust. It is based on the concept of ownership, which means that 
            each value in Rust has a single owner. When the owner goes out of scope, the value is dropped and the memory is freed. Ownership 
            is enforced at compile time, which means that Rust can catch memory errors before the program runs. Ownership is a key feature of  
            Rust that allows it to provide memory safety without using garbage collection.
        -   Rules:
            -   Each value in Rust has a single owner.
            -   When the owner goes out of scope, the value is dropped and the memory is freed.
            -   Ownership can be transferred from one variable to another using the move semantics.
            -   Ownership can be borrowed using references, which allows multiple variables to access the same value without taking ownership of it.
            -   Rust enforces ownership rules at compile time, which means that it can catch memory errors before the program runs.
        -   Example:
            -   "let s1 = String::from("Hello");"
            -   "let s2 = s1;" // Ownership of the String is moved from s1 to s2
            -   "println!("{}", s1);" // This line will cause a compile-time error because s1 no longer owns the String
            -   "let s3 = &s2;" // Borrowing the String using a reference
            -   "println!("{}", s3);" // This line is valid because s3 is a reference to the String owned by s2
            *   In functions, when you introduce variables, they are created on the stack. When you pass a variable to a function, it is moved to the function's stack frame. 
                When the function returns, the variable is dropped and the memory is freed. If you want to keep the variable after the function returns, 
                you can return it from the function or use references to borrow it.
                The way to return them is using "&" to return a reference to the variable, which allows the caller to access the variable without taking ownership of it.
    
    Variables life cycle:
        -   A variable in Rust has a scope, which is the region of the code where it is valid and can be used.
        -   A variable is created when it is declared and is destroyed when it goes out of scope.
        -   The lifetime of a variable begins at its declaration and ends at the closing brace that finishes its block.
        -   Variables declared inside a block exist only inside that block and are dropped automatically when the block ends.
        -   If a variable is moved to another variable, the original variable can no longer be used, because ownership has changed.
        -   If a variable is borrowed using a reference, the borrow is valid only while the reference is in scope.
        -   Rust uses lifetimes to ensure that references do not outlive the data they point to.
        -   This helps prevent dangling references and invalid memory access.
        -   Example:
            -   "let x = 5;"
            -   "{"
            -   "    let y = 10;"
            -   "    println!("{}", y);"
            -   "}"
            -   "println!("{}", x);" // x is still valid here
            -   "println!("{}", y);" // This would cause a compile-time error because y is out of scope
        -   Shadowing is also related to the variable lifecycle, because a new variable can be declared with the same name in a smaller scope.
        -   Example of shadowing:
            -   "let name = "Alice";"
            -   "let name = name.len();"
            -   "println!("{}", name);"
        -   In general, Rust automatically frees memory when a value goes out of scope, which makes memory management safer and more predictable.

    Panic:
        -   A panic occurs when the program encounters an unrecoverable error and stops its execution.
        -   In Rust, panics are used when something goes wrong that should not happen in normal execution.
        -   Common causes of panic include indexing an array out of bounds, dividing by zero, or calling unwrap() on an Option that is None.
        -   When a panic happens, Rust unwinds the stack and shows an error message with the file, line, and cause of the failure.
        -   Panics are useful for debugging because they immediately point to the problem in the program.
        -   Example:
            -   "let numbers = [1, 2, 3];"
            -   "println!("{}", numbers[10]);" // This will panic because the index is out of bounds
        -   Another example:
            -   "let value = Some(5);"
            -   "let result = value.unwrap();" // This is valid because Some(5) contains a value
            -   "let none_value = None;"
            -   "let result = none_value.unwrap();" // This will panic because None has no value
        -   In general, panics should be used for unexpected situations, while proper error handling with Result and Option is preferred for recoverable errors.
        -   You can use it like: panic!("Message you want to show"), and every next senteces are not shown. This makes it as an easy tool to debugs our programs.

    Option:
        -   Option is a Rust enum used to represent a value that may or may not be present.
        -   It is a safer alternative to null because it forces the programmer to handle the absence of a value explicitly.
        -   Option has two variants: Some(T) to represent a value, and None to represent the absence of a value.
        -   It is commonly used when a value may be missing, such as a user input, a search result, or a configuration value.
        -   Example:
            -   "let some_value = Some(5);"
            -   "let no_value: Option<i32> = None;"
        -   You can work with Option using methods like unwrap(), expect(), match, and if let.
        -   Example with match:
            -   "match some_value {"
            -   "    Some(value) => println!("The value is {}", value),"
            -   "    None => println!("There is no value"),"
            -   "}"
        -   Example with if let:
            -   "if let Some(value) = some_value {"
            -   "    println!("The value is {}", value);"
            -   "}"
        -   If you call unwrap() on None, the program will panic.
        -   You can implement them in structures following the next example:
            -   "struct User {"
            -   "    name: Option<String>,"
            -   "    age: Option<i32>,"
            -   "}"
            -   "let user = User {"
            -   "    name: Some(String::from("Alice")),"
            -   "    age: None,"
            -   "};"
            -   "match user.name {"
            -   "    Some(name) => println!("The user's name is {}", name),"
            -   "    None => println!("The user has no name"),"
            -   "}"
            -   "match user.age {"
            -   "    Some(age) => println!("The user is {} years old", age),"
            -   "    None => println!("The user's age is unknown"),"
            -   "}"
    Result:
        -   Result is a Rust enum used to represent operations that can succeed or fail.
        -   It is commonly used for error handling in functions that may return an error.
        -   Result has two variants: Ok(T) for success and Err(E) for failure.
        -   It is a safer and more explicit way to deal with errors than using exceptions.
        -   Example:
            -   "let ok_value: Result<i32, &str> = Ok(10);"
            -   "let error_value: Result<i32, &str> = Err("Something went wrong");"
        -   You can handle a Result using match, unwrap_or, expect, or the ? operator.
        -   Example with match:
            -   "match ok_value {"
            -   "    Ok(value) => println!("Success: {}", value),"
            -   "    Err(error) => println!("Error: {}", error),"
            -   "}"
        -   Example with unwrap_or:
            -   "let value = error_value.unwrap_or(0);"
        -   If you call unwrap() on an Err, the program will panic.
        -   Result and Option work well together: Option can represent missing data, while Result represents an operation that might fail.
        -   Example combining them:
            -   "fn find_name(name: Option<&str>) -> Result<&str, &str> {"
            -   "    match name {"
            -   "        Some(value) => Ok(value),"
            -   "        None => Err("Name not found"),"
            -   "    }"
            -   "}"
        -   Example with panic:
            -   "let result = find_name(None);"
            -   "let value = result.unwrap_or("Default");" // This handles the error safely
            -   "panic!("This line will not be executed if the error is handled");"
*/

//First Hello World program in Rust
fn main() {
    // The println! macro prints text to the console. Macros in Rust are invoked with a ! after their name.
    println!("Hello, world!");
}

