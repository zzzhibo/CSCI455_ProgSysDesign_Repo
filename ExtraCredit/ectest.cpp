// ectest.cpp
// CSCI 455 Fall 2019, Extra Credit assignment

/*
 *  A console-based interactive program you can use to test your list functions.
 *  To run the program:  ectest
 *
 *  Note: this uses separate compilation.  You put your code in ecListFuncs.cpp
 *  Code in this file calls those funcs.
 *  You do not need to modify or submit this file.
 */

#include <iostream>

// for istringstream
#include <sstream>

#include "ecListFuncs.h"

using namespace std;

int promptForInt (string prompt);
void buildList(ListType & theList);
void doHelp();
void doLastIndexOf(ListType list);
bool readAndDispatchCommand(ListType & theList);
void doMerge(ListType & list);
void doLongestRun(ListType list);



//*************************************************************************


/* a little test program */

int main ()
{

   bool keepgoing = true;

   ListType theList = NULL;

   doHelp();

   do {

      keepgoing = readAndDispatchCommand(theList);
    
      cout << "The list: ";
      printList (theList);
    
   } while (keepgoing);

   return 0;
}


/*
 * reads a command and executes it.
 * The command execution updates and/or uses theList, thus it's passed
 * by reference here.
 * Returns false iff the command entered was q (quit)
 */
bool readAndDispatchCommand(ListType & theList) {

   char cmd;

   cout << "\nPlease enter a command [b, l, r, i, m, p, h, q]: ";
   cin >> cmd;

   if (cmd == 'b') {
      clearList(theList);
      cout << "Please enter a sequence of zero or more ints followed by <nl> (e.g:1 2 3): ";
      buildList(theList);
   }
   else if (cmd == 'l') {
      doLongestRun(theList);
   }
   else if (cmd == 'r') {
      removeMultiplesOf3(theList);
   } 
   else if (cmd == 'i') {
      int val = promptForInt("Value to insert");
      insertMiddle(theList, val);
   }
   else if (cmd == 'm') {
      doMerge(theList);
   }
   else if (cmd == 'p') {
      cout << "Print list: " << endl;
      printList(theList); cout << endl;
   }
   else if (cmd == 'q') {
      return false;
   }
   else {
      if (cmd != 'h') {  cout << "ERROR: invalid command" << endl;  }
      doHelp();
   }

   return true;

}


/*
 * promptForInt: Prompts for and reads an integer.
 */
int promptForInt (string prompt)
{
   int value;

   cout << prompt << ": ";
   cin >> value;
   return value;
}



// read input from the user to build a list.
// input will be a space separated list of numbers all on one line.
// old value of theList is destroyed.
// (needs to be O(n))

// Note: this code uses istringstream: this is the analogous feature
//     to using a Scanner on a String in Java.
void buildList(ListType & theList) {

   string lineStr;

   getline(cin, lineStr);  // consume rest of previous line

   getline(cin, lineStr);

   if (lineStr.empty()) {
      theList = NULL;
      return;
   }

   istringstream istr(lineStr);

   Node *last = NULL;
   int i;

   istr >> i;   // first one is a special case
   theList = new Node(i);
   last = theList;

   while (istr >> i) { // comes out false if we reach end of string (i.e., eoln)
      last->next = new Node(i);
      last = last->next;
   }

}


// prints out a command summary
void doHelp() {
   cout << "Valid commands are" << endl;
   cout << "         b(uild new list), l(ongest run), r(emove multiples of 3)," 
        << endl;
   cout << "         i(nsert middle), m(erge), " << endl;
   cout << "         p(rint), h(elp), q(uit)." << endl;
}



// run longestRun on list, and print results
void doLongestRun(ListType list) {
   if (list == NULL) {
      cout << "ERROR: list must be non-empty."  << endl;
      return;
   }
   int maxRunVal;
   int maxRunLen;

   longestRun(list, maxRunVal, maxRunLen);

   cout<< "The longest run is of the value " << maxRunVal;
   cout << ", occuring " << maxRunLen << " times in sequence." << endl;
   
}


// prompt for a second list, and run merge on list and the other list
// list will be updated to be the merged list.
void doMerge(ListType & list) {

   ListType list2 = NULL;
   
   cout << "Note this will only work with two lists in increasing order with no duplicates." << endl;
   cout << "Enter the values for the other list as" << endl;
   cout << "a sequence of zero or more ints followed by <nl> (e.g:1 2 3): ";
   buildList(list2);
   list = merge(list, list2);
}


//*****************************************************************
// utility list funcs and Node methods
// (Note: prototypes and comments for these functions are in ecListFuncs.h)


void printList(ListType list) {

   if (list == NULL) {
      cout << "<empty>";
   }

   Node *p = list;
   while (p != NULL) {
      cout << p->data << " ";
      p = p->next;
   }
   cout << endl;
}


void clearList(ListType &list) {

   Node *rest = list;

   while (list != NULL) {
      rest = list->next;  // rest is all but the first element
      delete list;  // reclaims one node only
      list = rest;
   }

}

Node::Node(int item) { 
   data = item;
   next = NULL;
}

Node::Node(int item, Node *n) {
   data = item;
   next = n;
}
