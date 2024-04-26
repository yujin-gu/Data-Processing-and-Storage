# Data-Processing-and-Storage
## Overview
This Java program implements an in-memory key-value database that supports various functions such as starting transactions, storing or updating data, retrieving stored data, rolling back changes, and committing changes.

## How to Run
### 1. Clone the Repository:  
`git clone <repository-url>`
  
### 2. Navigate to the Directory:  
`cd in-memory-db`

### 3. Compile the Java File:  
`javac InMemoryDB.java`

### 4. Run the Program:  
`java InMemoryDB`

## How to Use
### 1. Start a Transaction:  
To begin a transaction, select option 1 from the menu. Only one transaction can be active at a time. Option 2, 4, and 5 won't be avaliable until a transaction has been started

### 2. Store/Update Data:  
Choose option 2 from the menu to store or update data in the database. Enter the key and value when prompted.

### 3. Get Stored Data:
Select option 3 to retrieve stored data from the database. Enter the key for which you want to retrieve the data.

### 4. Rollback Changes:
Option 4 allows you to rollback all changes made within the current transaction.

### 5. Commit Changes:
Option 5 commits all changes made within the current transaction to the main state. One transaction will be automatically stopped once changes made in this transaction are committed to the main database

### 6. Exit:
To exit the database, choose option 6.

## Sample Test Code
```
public class Main {
    public static void main(String[] args) {
        // Create an instance of InMemoryDB
        InMemoryDB inMemoryDB = new InMemoryDB();

        // Sample usage of database functions
        inMemoryDB.beginTransaction();
        inMemoryDB.put("A", 5);
        System.out.println(inMemoryDB.get("A")); // should return null
        inMemoryDB.put("A", 6);
        inMemoryDB.commit();
        System.out.println(inMemoryDB.get("A")); // should return 6
    }
}
```

## Advice about this assignment 
To modify this assignment to become an “official” assignment in the future, some changes can include:
1. More detailed instructions: Add more detailed instructions for each function about its behavior, for example, more examples of the output of each function. A flow chart would also be very helpful for students to quickly understand the instructions of this assignment.  
2. Grading Rubric: Add a detailed grading rubric. Right now the rubric only says 4 points for working code without furthur definition of a "working code". In addition, the grading can be further broken down into smaller sections. For example, 2 points if the rollback and get function work and pass all the tests.
