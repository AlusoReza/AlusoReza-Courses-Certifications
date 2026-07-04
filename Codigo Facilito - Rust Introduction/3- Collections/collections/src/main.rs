fn main() {
    /*
    Collections in Rust are data structures used to store multiple values in one variable.
    Each collection has a different purpose, depending on whether you need a fixed size,
    a dynamic list, key-value pairs, or unique values.

    1. Arrays
       Arrays are fixed-size collections of elements of the same type.
       They are useful when you already know the number of elements and want fast access.

       Ways to define:
           - let numbers = [1, 2, 3, 4, 5];
           - let numbers: [i32; 5] = [1, 2, 3, 4, 5];
           - let repeated = [5; 3];

       Ways to access:
           - numbers[0] -> gets the first element
           - numbers.get(1) -> gets the second element safely, without panicking
           - numbers.len() -> returns the size of the array
           - numbers.iter() -> iterates over all values

       Ways to modify:
           - numbers[0] = 10; -> changes the first element
           - numbers[1..3].copy_from_slice(&[7, 8]); -> replaces a slice of values

       Ways to use:
           - Use arrays when the size is known in advance and will not change.

    2. Tuples
       Tuples are fixed-size collections that can store values of different types.
       They are useful when you want to group related values together.

       Ways to define:
           - let person = ("Ana", 25, true);
           - let person: (i32, f64, &str) = (42, 3.14, "Hello");

       Ways to access:
           - person.0 -> first element
           - person.1 -> second element
           - person.2 -> third element

       Ways to modify:
           - let mut person = ("Ana", 25, true);
           - person.0 = "Luis"; -> changes the first element
           - person.1 = 30; -> changes the second element

       Ways to use:
           - Use tuples when you want to return multiple values or group different types together.

       Extra way:
           - let (name, age, active) = person; -> destructuring a tuple

    3. Vectors
       Vectors are dynamic-size collections of values of the same type.
       They are useful when you need to add or remove elements at runtime.

       Ways to define:
           - let mut vector = Vec::new();
           - let mut vector = vec![1, 2, 3, 4, 5];
           - let mut vector_with_capacity = Vec::with_capacity(10);

       Ways to access:
           - vector[0] -> gets the first element
           - vector.get(1) -> gets an element safely
           - vector.len() -> returns the number of elements
           - vector.iter() -> iterates over elements

       Ways to modify:
           - vector.push(6); -> adds an element at the end
           - vector.pop(); -> removes the last element
           - vector.insert(2, 99); -> inserts an element at a specific index
           - vector.remove(2); -> removes an element at a specific index
           - vector.clear(); -> removes all elements
           - vector.extend([7, 8, 9]); -> adds multiple values
           - vector.sort(); -> sorts the elements
           - vector.retain(|x| *x % 2 == 0); -> keeps only the values that satisfy a condition

       Ways to use:
           - Use vectors when the size can change while the program is running.

    4. Hash Maps
       Hash maps store data as key-value pairs.
       They are useful when you need to search values by a key.

       Ways to define:
           - use std::collections::HashMap;
           - let mut map = HashMap::new();
           - let mut map = HashMap::from([("one", 1), ("two", 2)]);

       Ways to access:
           - map.get("one") -> gets a value by key
           - map.contains_key("one") -> checks if a key exists
           - map.len() -> returns the number of pairs
           - map.iter() -> iterates over key-value pairs

       Ways to modify:
           - map.insert("key".to_string(), 42); -> inserts or updates a value
           - map.remove("key"); -> removes a key-value pair
           - map.clear(); -> removes all pairs
           - map.entry("key".to_string()).or_insert(100); -> inserts a value only if the key is missing

       Ways to use:
           - Use hash maps when you want fast lookup by key.

    5. Hash Sets
       Hash sets store unique values.
       They are useful when you need to keep a collection of distinct items.

       Ways to define:
           - use std::collections::HashSet;
           - let mut set = HashSet::new();
           - let mut set = HashSet::from([1, 2, 3]);

       Ways to access:
           - set.contains(&5) -> checks if a value exists
           - set.len() -> returns the number of elements
           - set.iter() -> iterates over the elements

       Ways to modify:
           - set.insert(5); -> adds a value
           - set.remove(&5); -> removes a value
           - set.clear(); -> removes all values

       Ways to use:
           - Use hash sets when you need to avoid duplicates.

    6. Slices
       Slices are views into a collection. They do not own the data,
       but they allow you to work with a portion of a collection.

       Ways to define:
           - let slice = &numbers[0..3];
           - let slice: &[i32] = &vector[1..4];

       Ways to access:
           - slice[0] -> gets the first value of the slice
           - slice.len() -> returns the number of elements in the slice

       Ways to use:
           - Use slices when you want to borrow part of an array or vector without copying it.

    Printing and iteration:
        - println!("{:?}", arr); -> prints the contents of an array
        - println!("{:?}", tup); -> prints the contents of a tuple
        - println!("{:?}", vector); -> prints the contents of a vector
        - println!("{:?}", map); -> prints the contents of a hash map
        - println!("{:?}", set); -> prints the contents of a hash set
        - for item in vector.iter() { println!("{}", item); } -> iterates through a collection
    */

    //Example of using a collections:
    let arr: [i32; 5] = [1, 2, 3, 4, 5];
    let vec: Vec<i32> = vec![1, 2, 3, 4, 5];
    let tup: (i32, f64, &str) = (42, 3.14, "Hello");
    let mut set: std::collections::HashSet<i32> = std::collections::HashSet::new();

}
