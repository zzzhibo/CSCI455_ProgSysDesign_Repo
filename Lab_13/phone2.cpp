/*

  CSCI 455 C String lab

  See lab description for what it should do.  
  C++ tools allowed: cout, call by reference, ostream output

*/

// for C input functions (e.g., fgets used here)
#include <cstdio>

// C string functions
#include <cstring>

#include <iostream>

using namespace std;

const char NAME_DELIM = ':';
const int AREA_CODE_SIZE = 3;
const int PREFIX_SIZE = 3;
const int LINE_NO_SIZE = 4;
const int MAX_LINE_SIZE = 1024; // including newline and terminating null char

void readField(char *buffer, int startLoc, int fieldLen, char *destChar);

int main()
{

    char buffer[MAX_LINE_SIZE];

    // fgets reads a line of input and puts it in a C string.  If the line of input
    // is longer than the buffer array only gets enough chars that fit (and ignores the
    // rest); this prevents it from overwriting the end of the array.
    // Unlike Java Scanner nextLine(), fgets also saves the newline in the buffer.
    // So after call, buffer will have 0 or more chars read from the line,
    // then a newline char ('\n'), and then the null char ('\0')
    // note: the sizeof function below does not work with dynamic arrays.
    // fgets returns 0 (false) when it hits EOF
    // Note: stdin (third paremeter below) is the C version of cin or System.in

    while (fgets(buffer, sizeof(buffer), stdin))
    {
        //xxx-xxx-xxxx
        cout << "--------------------" << endl;
        cout << "LINE READ: " << buffer;

        char areacodePT[AREA_CODE_SIZE + 1];
        char prefixPT[PREFIX_SIZE + 1];
        char linenoPT[LINE_NO_SIZE + 1];

        // memcpy(areacodePT, &buffer[0], 3);
        // areacodePT[3] = '\0';

        // memcpy(prefixPT, &buffer[4], 3);
        // areacodePT[3] = '\0';

        // memcpy(linenoPT, &buffer[8], 4);
        // areacodePT[4] = '\0';

        readField(buffer, 0, AREA_CODE_SIZE, areacodePT);
        readField(buffer, 4, PREFIX_SIZE, prefixPT);
        readField(buffer, 8, LINE_NO_SIZE, linenoPT);

        cout << "area code: " << areacodePT << endl;
        cout << "prefix code: " << prefixPT << endl;
        cout << "line Number: " << linenoPT << endl;
    }
}

void readField(char *buffer, int startLoc, int fieldLen, char *destChar)
{
    int j = 0;
    // char *sm = destChar;
    for (int i = startLoc; i < startLoc + fieldLen; i++)
    {
        destChar[j] = buffer[i];
        j++;
    }
    destChar[fieldLen] = '\0';
    // sm[0] = 0;
}
