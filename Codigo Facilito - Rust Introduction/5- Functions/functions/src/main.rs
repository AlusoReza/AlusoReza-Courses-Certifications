fn main() {
    /*
    Functions in Rust are blocks of code that can be defined and called to perform a specific task. 
    Functions are defined using the fn keyword, followed by the function name, a set of parentheses 
    for parameters, and a block of code enclosed in curly braces {}. Functions can take parameters, 
    return values, and be called from other parts of the program.

    Characteristics:
        -   Functions can take parameters, which are values passed into the function when it is called. 
            Parameters are defined in the parentheses after the function name, and they can have a type 
            specified. For example, we can define a function that takes two integers as parameters and 
            returns their sum as follows:
                -   "fn add(a: i32, b: i32) -> i32 {"
                -   "    a + b"
                -   "}"
        -   Functions can also return values, which are the results of the function's execution. The return 
            type is specified after the arrow (->) in the function definition. For example, we can define a 
            function that returns a string as follows:
                -   "fn greet(name: &str) -> String {"
                -   "    format!("Hello, {}!", name)"
                -   "}"
        -   Functions can be called from other parts of the program by using their name followed by parentheses
            and any required arguments. For example, we can call the add function as follows:
                -   "let result = add(5, 10);"
        -   Functions can also be defined with no parameters and no return value. For example, we can define a 
            function that prints a message to the console as follows:
                -   "fn say_hello() {"
                -   "    println!("Hello, world!");"
                -   "}"
        -   Functions can be defined inside other functions, which is known as a nested function. Nested 
            functions can access variables from their parent function, but they cannot be called from outside their 
            parent function. For example, we can define a nested function as follows:
            -   "fn outer_function() {"
            -   "    let x = 5;"
            -   "    fn inner_function() {"
            -   "        println!("The value of x is: {}", x);"
            -   "    }"
            -   "    inner_function();"
            -   "}"
        -   Functions can also be defined with default parameter values, which are used when the caller does not 
            provide a value for that parameter. For example, we can define a function with a default parameter 
            value as follows:
            -   "fn greet(name: &str, greeting: &str = "Hello") -> String {"
            -   "    format!("{}, {}!", greeting, name)"
            -   "}"
        -   Functions can also be defined with variable-length parameter lists, which allow the caller to provide 
            any number of arguments for that parameter. For example, we can define a function with a variable-length 
            parameter list as follows:
            -   "fn sum(numbers: &[i32]) -> i32 {"
            -   "    numbers.iter().sum()"
            -   "}"
        -   Functions can also be defined with generic types, which allow the function to work with different types of 
            data. For example, we can define a function with a generic type as follows:
                -   "fn print_value<T: std::fmt::Debug>(value: T) {"
                -   "    println!("{:?}", value);"
        -   Functions can also be defined with lifetimes, which specify how long a reference to a value is valid. 
            For example, we can define a function with a lifetime as follows:
                -   "fn longest<'a>(s1: &'a str, s2: &'a str) -> &'a str {"
                -   "    if s1.len() > s2.len() {"
                -   "        s1"
                -   "    } else {"
                -   "        s2"
                -   "    }"
        -   Functions can also be defined with closures, which are anonymous functions that can capture variables 
            from their surrounding environment. For example, we can define a closure as follows:
                -   "let add = |a: i32, b: i32| -> i32 {"
                -   "    a + b"
                -   "};"
        
    Types of functions:
        -   Regular functions: are defined using the fn keyword and can be called from other parts of the program.
        -   Closures: are anonymous functions that can capture variables from their surrounding environment. 
            They are defined using the | | syntax and can be assigned to variables or passed as arguments to 
            other functions.
        -   Higher-order functions: are functions that take other functions as arguments or return functions as their 
            result. They can be used to create more abstract and reusable code.
        -   Recursive functions: are functions that call themselves in order to solve a problem. They can be used to solve 
            problems that can be broken down into smaller subproblems.
        -   Async functions: are functions that can be executed asynchronously, allowing for non-blocking I/O operations. 
            They are defined using the async keyword and can be used with the await keyword to wait for the result of an 
            asynchronous operation.
        -   Unsafe functions: are functions that can perform operations that are not checked by the Rust compiler for safety. 
            They are defined using the unsafe keyword and can be used to perform low-level operations that require direct 
            access to memory or hardware.
        -   Extern functions: are functions that are defined in external libraries or other programming languages. 
            They are defined using the extern keyword and can be used to call functions from other languages or libraries.
        -   Trait functions: are functions that are defined in traits, which are used to define shared behavior between different 
            types. They can be implemented by different types to provide a common interface for working with those types.
        -   Associated functions: are functions that are defined in a struct or enum and can be called using the type name. 
            They are defined using the impl keyword and can be used to provide additional functionality for a type.
        -   Methods: are functions that are defined in a struct or enum and can be called using an instance of that type. 
            They are defined using the impl keyword and can be used to provide behavior for a specific instance of a type.
        -   Inline functions: are functions that are defined using the #[inline] attribute, which suggests to the compiler that 
            the function should be inlined at the call site. This can improve performance by reducing function call overhead, but 
            it can also increase code size if used excessively.
    
    Calling a function:
        -   To call a function, we use its name followed by parentheses and any required arguments. For example, we can call the add 
            function as follows:
            -   "let result = add(5, 10);"
        -   We can also call a closure by using its variable name followed by parentheses and any required arguments. For example, 
            we can call the add closure as follows:
            -   "let result = add(5, 10);"
        -   We can also call a higher-order function by passing a function or closure as an argument. For example, we can call the 
            map function on a vector as follows:
            -   "let numbers = vec![1, 2, 3, 4, 5];"
            -   "let squared_numbers: Vec<i32> = numbers.iter().map(|x| x * x).collect();"
        -   We can also call a recursive function by calling the function from within itself. For example, we can call the factorial 
            function as follows:
            -   "let result = factorial(5);"
        -   We can also call an async function by using the await keyword to wait for the result of the asynchronous operation. For example, 
            we can call the fetch_data function as follows:
            -   "let data = fetch_data().await;"
        -   We can also call an unsafe function by using the unsafe keyword to indicate that we are aware of the potential risks of 
            calling the function. For example, we can call the dereference function as follows:
            -   "unsafe {"
            -   "    let value = dereference(ptr);"
            -   "}"
        -   We can also call an extern function by using the extern keyword to indicate that we are calling a function from an external 
            library or programming language. For example, we can call the printf function from the C standard library as follows:
            -   "extern "C" {"
            -   "    fn printf(format: *const i8, ...) -> i32;"
            -   "}"
            -   "let message = CString::new("Hello, world!\n").unwrap();"
            -   "unsafe {"
            -   "    printf(message.as_ptr());"
            -   "}"
        -   We can also call a trait function by using the trait name followed by the function name and any required arguments. 
            For example, we can call the to_string function from the ToString trait as follows:
            -   "let number = 42;"
            -   "let string = number.to_string();"
        -   We can also call an associated function by using the type name followed by the function
            name and any required arguments. For example, we can call the new function from the String type as follows:
            -   "let string = String::new();)"
        -   We can also call a method by using the instance of the type followed by the function name and any required arguments. 
            For example, we can call the push_str method from the String type as follows:
            -   "let mut string = String::new();"
            -   "string.push_str("Hello, world!");"
    
    -   Return: Functions can return values using the return statement or by simply placing the value at the end of the function. 
        The return type is specified after the arrow (->) in the function definition. For example, we can define a function 
        that returns an integer as follows:
            -   "fn add(a: i32, b: i32) -> i32 {"
            -   "    a + b"
            -   "}"
        We can also define a function that returns a string as follows:
            -   "fn greet(name: &str) -> String {"
            -   "    format!("Hello, {}!", name)"
            -   "}"

    Structures (struct): 
        -   Structures are user-defined data types that allow us to group related data together. They are defined using the struct keyword, 
            followed by the structure name and a set of curly braces {} containing the fields of the structure. For example, we can define 
            a structure to represent a point in 2D space as follows:
                -   "struct Point {"
                -   "    x: f64,"
                -   "    y: f64,"
                -   "}"
        -   We can create an instance of a structure by using the structure name followed by a set of curly braces {} containing the values 
            for each field. For example, we can create an instance of the Point structure as follows:
                -   "let point = Point { x: 1.0, y: 2.0 };"
        -   We can access the fields of a structure using dot notation. For example, we can access the x field of the point instance as follows:
                -   "let x_value = point.x;"
        -   We can also define methods for a structure using the impl keyword. Methods are functions that are associated with a specific 
            instance of a structure and can access its fields. For example, we can define a method to calculate the distance from the origin 
            for the Point structure as follows:
                -   "impl Point {"
                -   "    fn distance_from_origin(&self) -> f64 {"
                -   "        (self.x.powi(2) + self.y.powi(2)).sqrt()"
                -   "    }"
                -   "}"
        -   You can define variables outside the structure:
            -   "let x = 1.0;"
            -   "let y = 2.0;"
            -   "Point { x, y }" creates a new instance of the Point structure using the values of the x and y variables. This is known as field init shorthand.
        -   You can implement methods in structures:
            -   "impl Point {"
            -   "    fn distance_from_origin(&self) -> f64 {"
            -   "        (self.x.powi(2) + self.y.powi(2)).sqrt()"
            -   "    }"
            -   "}"
            -   You can use the methods in structures:
                -   "let point = Point { x: 3.0, y: 4.0 };"
                -   "let distance = point.distance_from_origin();"
                -   "println!("Distance from origin: {}", distance);"
            *   self. is a reference to the instance of the structure that the method is being called on. It allows the method to access the fields of that instance.
        -   You must use #[derive(Debug)] to print the structure:
            -   "#[derive(Debug)]"
            -   "struct Point {"
            -   "    x: f64,"
            -   "    y: f64,"
            -   "}"
            -   "let point = Point { x: 1.0, y: 2.0 };"
            -   "println!("{:?}", point);"
        -   Tuple structs: are a type of structure that allows us to define a structure with unnamed fields. They are defined using the struct keyword, 
            followed by the structure name and a set of parentheses () containing the types of the fields. For example, we can define a tuple 
            struct to represent a color as follows:
                -   "struct Color(u8, u8, u8);"
            -   We can create an instance of a tuple struct by using the structure name followed by a set of parentheses () containing the values 
                for each field. For example, we can create an instance of the Color tuple struct as follows:
                -   "let color = Color(255, 0, 0);"
            -   We can access the fields of a tuple struct using dot notation and the index of the field. For example, we can access the first 
                field of the color instance as follows:
                -   "let red_value = color.0;"
    
    
    */

    // Example: regular function
    fn add(a: i32, b: i32) -> i32 {
        a + b
    }
    let result = add(5, 10);
    println!("Regular function result: {}", result);

    // Example: closure
    let add_closure = |a: i32, b: i32| -> i32 { a + b };
    println!("Closure result: {}", add_closure(3, 4));

    // Example: higher-order function
    fn apply<F>(f: F, value: i32) -> i32
    where
        F: Fn(i32) -> i32,
    {
        f(value)
    }
    let doubled = apply(|x| x * 2, 6);
    println!("Higher-order function result: {}", doubled);

    // Example: recursive function
    fn factorial(n: u32) -> u32 {
        if n <= 1 {
            1
        } else {
            n * factorial(n - 1)
        }
    }
    println!("Recursive function result: {}", factorial(5));

    // Example: async function
    async fn fetch_data() -> &'static str {
        "data loaded"
    }
    let _future_data = fetch_data();
    println!("Async function example created");

    // Example: unsafe function
    unsafe fn unsafe_read(ptr: *const i32) -> i32 {
        *ptr
    }
    let value = 42;
    let unsafe_value = unsafe { unsafe_read(&value as *const i32) };
    println!("Unsafe function result: {}", unsafe_value);

    // Example: extern function
    extern "C" {
        fn puts(s: *const i8) -> i32;
    }
    unsafe {
        let message = b"Hello from extern\0";
        puts(message.as_ptr().cast());
    }

    // Example: trait function
    trait Greet {
        fn greet(&self) -> String;
    }
    impl Greet for String {
        fn greet(&self) -> String {
            format!("Hello, {}!", self)
        }
    }
    let greeting = String::from("Rust").greet();
    println!("Trait function result: {}", greeting);

    // Example: associated function and method
    struct Person;
    impl Person {
        fn new() -> Self {
            Person
        }

        fn speak(&self) {
            println!("Method example");
        }
    }
    let person = Person::new();
    person.speak();

    // Example: inline function
    #[inline]
    fn inline_example() {
        println!("Inline function example");
    }
    inline_example();
}
