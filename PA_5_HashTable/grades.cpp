// Name: Zhibo Wang
// USC NetID: zhibowan
// CSCI 455 PA5
// Fall 2019

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

// the function takes user input and find which step to take for the next
void programRun(Table *&grades);

// insert function which inserts student name with the score if new
void insertNew(Table *&grades, string name, int score);
// update the student's score if the student is in the table
void changeName(Table *&grades, string name, int score);
// check if the student is in the table
void lookupName(Table *&grades, string name);
// remove the student from the table if exisitent
void removeName(Table *&grades, string name);
// print all names and scores
void printGrades(Table *&grades);
// print the number of inputs
void sizeGrades(Table *&grades);
// print the hashstats of the table
void statsGrades(Table *&grades);
// helper program which prompts the manual to the user
void helpPrg();

int main(int argc, char *argv[])
{

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table *grades; // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1)
   {
      hashSize = atoi(argv[1]); // atoi converts c-string to int

      if (hashSize < 1)
      {
         cout << "Command line argument (hashSize) must be a positive number"
              << endl;
         return 1;
      }

      grades = new Table(hashSize);
   }
   else
   { // no command line args given -- use default table size
      grades = new Table();
   }

   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*

   // helpPrg();
   programRun(grades);

   return 0;
}

void programRun(Table *&grades)
{
   bool handle = true;
   string cmd;
   string name;
   int score;

   while (handle)
   {
      cout << "cmd> ";
      cin >> cmd;

      if (cmd == "insert")
      {
         cin >> name;
         cin >> score;
         insertNew(grades, name, score);
      }
      else if (cmd == "change")
      {
         cin >> name;
         cin >> score;
         changeName(grades, name, score);
      }
      else if (cmd == "remove")
      {
         cin >> name;
         removeName(grades, name);
      }
      else if (cmd == "lookup")
      {
         cin >> name;
         lookupName(grades, name);
      }
      else if (cmd == "print")
      {
         printGrades(grades);
      }
      else if (cmd == "size")
      {
         sizeGrades(grades);
      }
      else if (cmd == "stats")
      {
         statsGrades(grades);
      }
      else if (cmd == "help")
      {
         helpPrg();
      }
      else if (cmd == "quit")
      {
         handle = false;
      }
      else
      {
         cout << "ERROR: invalid command" << endl;
         helpPrg(); // if the input command is invalid, prints out the use instruction
      }
   }
}

void insertNew(Table *&grades, string name, int score)
{
   if (grades->insert(name, score))
   {
      cout << "Add Successfully" << endl;
   }
   else
   {
      cout << "Student " << name << " is already in the table" << endl; // false only if the student is already in the table
   }
}

void changeName(Table *&grades, string name, int score)
{
   if (grades->lookup(name) == NULL)
   {
      cout << name << " is not in the table" << endl; // if the student is not in the table, the score can't be updated
   }
   else
   {
      int *pt = grades->lookup(name);
      *pt = score; // use pointer to update the score

      cout << name << "'s score changed to " << *grades->lookup(name) << endl;
   }
}

void lookupName(Table *&grades, string name)
{
   if (grades->lookup(name) != NULL)
   {
      cout << name << " is in the table!" << endl;
   }
   else
   {
      cout << name << " is not in the table!" << endl;
   }
}

void removeName(Table *&grades, string name)
{
   if (grades->remove(name))
   {
      cout << name << " removed from the table" << endl;
   }
   else
   {
      cout << "can not remove the student that is not in the table!" << endl;
   }
}

void printGrades(Table *&grades)
{
   grades->printAll();
}

void sizeGrades(Table *&grades)
{
   cout << "The size of the table is " << grades->numEntries() << endl;
}

void statsGrades(Table *&grades)
{
   grades->hashStats(cout);
}

void helpPrg()
{
   cout << endl;
   cout << "\t commands to use the grades program " << endl;
   cout << "\t insert name score: insert" << endl;
   cout << "\t change name score: change" << endl;
   cout << "\t remove name: remove" << endl;
   cout << "\t lookup name: lookup" << endl;
   cout << "\t print all: print" << endl;
   cout << "\t size of the table: size" << endl;
   cout << "\t stats of the table: stats" << endl;
   cout << "\t display hints of how to use the program: help" << endl;
   cout << "\t quit the program: quit" << endl;
   cout << endl;
}