fn main() {
    /*

    Flow control structures in Rust are used to control the flow of execution in a program. 
    ]Rust provides several flow control structures, including if statements, loops, and match expressions. 
    Each flow control structure has its own strengths and weaknesses, and the choice of which one to use 
    depends on the specific use case.

    -   Conditionals: are used to execute different blocks of code based on a condition. 
        Rust provides the if statement for this purpose. The if statement evaluates a condition and executes 
        the block of code associated with it if the condition is true. If the condition is false, the block 
        of code associated with the else statement (if present) is executed instead. For example, we can use 
        an if statement to check if a number is positive or negative as follows:
            -   "let number = 5;"
            -   "if number > 0 {"
            -   "    println!("The number is positive.");"
            -   "} else if number < 0 {"
            -   "    println!("The number is negative.");"
            -   "} else {"
            -   "    println!("The number is zero.");"
            -   "}"

    -   Loops: are used to execute a block of code repeatedly. Rust provides several types of loops, including
        the for loop, while loop, and loop statement. The for loop is used to iterate over a range of values or 
        a collection of items. The while loop is used to execute a block of code while a condition is true. The 
        loop statement is used to create an infinite loop that can be exited using the break statement. For example, 
        we can use a for loop to iterate over an array of numbers as follows:
        -   for:
            -   "let numbers = [1, 2, 3, 4, 5];"
            -   "for number in numbers.iter() {"
            -   "    println!("The number is: {}", number);"
            -   "}"
            -   "for number in 0..5 {"
            -   "    println!("The number is: {}", number);"
            -   "}"
        -   while:
            -   "let mut number = 0;"
            -   "while number < 5 {"
            -   "    println!("The number is: {}", number);"
            -   "    number += 1;"
            -   "}"
        -   loop:
            -   "let mut number = 0;"
            -   "loop {"
            -   "    if number >= 5 {"
            -   "        break;"
            -   "    }"
            -   "    println!("The number is: {}", number);"
            -   "    number += 1;"
            -   "}"
            
    -   Match expressions: are used to compare a value against a series of patterns and execute the block of code 
        associated with the first matching pattern. 
        Match expressions are similar to switch statements in other programming languages. For example, we can use 
        a match expression to check the value of a variable and execute different blocks of code based on its value 
        as follows:
            -   "let number = 5;"
            -   "match number {"
            -   "    1 => println!("The number is one."),"
            -   "    2 => println!("The number is two."),"
            -   "    3 => println!("The number is three."),"
            -   "    4 | 5 | 6 => println!("The number is between 4 and 6."),"
            -   "    7..=100 => println!("The number is between 7 and 100."),"
            -   "    _ => println!("The number is something else."),"
            -   "}"
            -   "let mensaje= match number {"
            -   "    1 => "The number is one.","
            -   "    2 => "The number is two.","
            -   "    3 => "The number is three.","
            -   "    4 | 5 | 6 => "The number is between 4 and 6.","
            -   "    7..=100 => "The number is between 7 and 100.","
            -   "    _ => "The number is something else.","
            -   "};"
            -   "println!("Message: {}", mensaje);"
            -   "match (number1, number2) {
            -   "    (0, 0) => println!("Both numbers are zero."),
            -   "    (0, _) => println!("The first number is zero."),"
            -   "    (_, 0) => println!("The second number is zero."),"
            -   "    (_, _) => println!("Both numbers are non-zero."),"
            -   "};"
        
    -   Enum matching: is a powerful feature of Rust that allows us to define a type that can have multiple variants. 
        We can use match expressions to match against the different variants of an enum and execute different blocks 
        of code based on the variant. For example, we can define an enum called Color with three variants: Red, Green, 
        and Blue. We can then use a match expression to check the value of a Color variable and execute different blocks 
        of code based on its variant as follows:
            -   "enum Color {"
            -   "    Red,"
            -   "    Green,"
            -   "    Blue,"
            -   "}"
            -   "let color = Color::Green;"
            -   "match color {"
            -   "    Color::Red => println!("The color is red."),"
            -   "    Color::Green => println!("The color is green."),"
            -   "    Color::Blue => println!("The color is blue."),"
            -   "}"
            -   "enum Response {"
            -   "    Success,"
            -   "    Error(u32), //403, 404, 500"
            -   "}"
            -   "let response = Response::Error(404);"
            -   "match response {"
            -   "    Response::Success => println!("The request was successful."),"
            -   "    //Response::Error(code) => println!("The request failed with error code: {}", code),"
            -   "    Response::Error(404) => println!("The requested resource was not found."),"
            -   "    Response::Error(403) => println!("The request was forbidden."),"
            -   "    Response::Error(500) => println!("The server encountered an internal error."),"
            -   "    Response::Error(_) => println!("The request failed with an unknown error code."),"
            -   "}"

        -   Some:
            -   Some is one of the two variants of the Option enum.
            -   It is used to indicate that a value exists and contains data.
            -   In other words, Some(T) means "there is a value of type T".
            -   It is the opposite of None, which means that there is no value.
            -   Example:
                -   "let age = Some(25);"
                -   "let name = Some("Alice");"
            -   You can extract the contained value from Some using match, if let, or unwrap().
            -   Example with match:
                -   "match age {"
                -   "    Some(value) => println!("The age is {}", value),"
                -   "    None => println!("No age provided"),"
                -   "}"
            -   Example with unwrap:
                -   "let value = Some(10).unwrap();"
                -   "println!("{}", value);"
            -   Some is commonly used together with Option to represent values that may be present or absent.
            -   We can integrate Some and match as follows:
                -   "let maybe_age = Some(25);"
                -   "match maybe_age {"
                -   "    Some(age) => println!("The age is {}", age),"
                -   "    None => println!("No age provided"),"
                -   "}"
            -   This is useful because match allows us to handle both the case where a value exists and the case where it does not.
            -   It is a very common pattern in Rust when working with Option values.
        
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
        -   Methods:
            -   unwrap(): extracts the Ok value, but panics if the result is Err.
            -   expect("message"): extracts the Ok value, but panics with a custom message if the result is Err.
            -   unwrap_or(default): returns the Ok value or a default value if the result is Err.
            -   unwrap_or_else(|err| ...): returns the Ok value or computes a fallback value from the error.
            -   is_ok() and is_err(): check whether the result is Ok or Err without consuming it.
            -   map(): transforms the Ok value if the result is Ok.
            -   map_err(): transforms the Err value if the result is Err.
            -   and_then(): chains another Result operation when the current value is Ok.
            -   or_else(): provides an alternative Result if the current result is Err.

    -   Break and continue statements: are used to control the flow of loops. The break statement is used to exit 
        a loop prematurely, while the continue statement is used to skip the current iteration of a loop and move 
        on to the next one. For example, we can use a break statement to exit a loop when a certain condition is 
        met as follows:
            -   "let mut number = 0;"
            -   "while number < 10 {"
            -   "    if number == 5 {"
            -   "        break;"
            -   "    }"
            -   "    println!("The number is: {}", number);"
            -   "    number += 1;"
            -   "}"
    -   The continue statement can be used to skip the current iteration of a loop and move on to the next one. 
        For example, we can use a continue statement to skip even numbers in a loop as follows:
            -   "let mut number = 0;"
            -   "while number < 10 {"
            -   "    number += 1;"
            -   "    if number % 2 == 0 {"
            -   "        continue;"
            -   "    }"
            -   "    println!("The number is: {}", number);"
            -   "}" 

    -   Blocks: are used to group a series of statements together. Blocks are defined using curly braces {} and
        can be used in 
        conjunction with flow control structures to create more complex programs. For example, we can use a block 
        to group a series of statements together in an if statement as follows:
            -   "let number = 5;"
            -   "if number > 0 {"
            -   "    println!("The number is positive.");"
            -   "    println!("This is a block of code.");"
            -   "}"
        Or in any part of the program, for example, we can use a block to group a series of statements together
        in a function as follows:
            -   "fn main() {"
            -   "    let number = 5;"
            -   "    {"
            -   "        println!("The number is: {}", number);"
            -   "        println!("This is a block of code.");"
            -   "    }"
            -   "}"
                -   Any variable defined inside a block is only accessible within that block, and it will be 
                    dropped when the block ends. This is useful for creating temporary variables that are only 
                    needed for a specific part of the program.
                -   Blocks can also be used to create new scopes for variables, which can help prevent naming 
                    conflicts and make code easier to read and maintain.
                -   You can return a value from a block by using the return statement or by simply placing the 
                    value at the end of the block. For example, we can use a block to return a value from a 
                    function as follows:
                    -   "fn main() {"
                    -   "    let number = 5;"
                    -   "    let result = {"
                    -   "        let temp = number * 2;"
                    -   "        temp + 1"
                    -   "    };"
                    -   "    println!("The result is: {}", result);"
                    -   "}"
    

    */

    //Example of each flow control structures in Rust:
    let number = 5;
    if number > 0 {
        println!("The number is positive.");
    } else if number < 0 {
        println!("The number is negative.");
    } else {
        println!("The number is zero.");
    }

    let mut number = 0;
    while number < 5 {
        println!("The number is: {}", number);
        number += 1;
    }
    
    let numbers = [1, 2, 3, 4, 5];
    for number in numbers.iter() {
        println!("For loop value: {}", number);
    }

    let mut loop_counter = 0;
    loop {
        println!("Loop value: {}", loop_counter);
        loop_counter += 1;
        if loop_counter == 3 {
            break;
        }
    }

    let day = 3;
    match day {
        1 => println!("Monday"),
        2 => println!("Tuesday"),
        3 => println!("Wednesday"),
        _ => println!("Another day"),
    }

    let mut continue_counter = 0;
    while continue_counter < 6 {
        continue_counter += 1;
        if continue_counter % 2 == 0 {
            continue;
        }
        println!("Continue example: {}", continue_counter);
    }

    let age = 20;
    {
        let is_adult = age >= 18;
        println!("Block example: {}", is_adult);
    }
}
