Directory.io is a site that contains all bitcoin private keys
Bruteforcing it is quasi-impossible because there is 904625697166532776746648320380374280100293470930272690489102837043110636675
pages and each page contains 256 adress
so the unique chance to get a page that contains bitcoins is to test random pages
so this script chose a random page , test all adresses on it and chose another etc etc...
Usage : 
java DirectoryIoBruteForce only 
to show only pages and adresses that contains money
java DirectoryIoBruteForce all
to show all test cases
then if the script catch an adres you can copy page number and visit 
http://directory.io/:page
finnaly get the private key .
ps: if script don't work change you user agent in line 54 