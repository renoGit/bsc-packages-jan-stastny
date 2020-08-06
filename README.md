#Package delivery



User enters line consisting of weight of package and
destination postal code into console like `12.56 08801` and press 'ENTER'.

If you specify initial arguments, you can load initial data:

Initial package delivery file sample:
``` 
3.4 08801
2 90005
12.56 08801
5.5 08079 
3.2 09300
```

Initial fees file sample:
```
10 5.00
5 2.50
3 2.00
2 1.50
1 1.00
0.5 0.70
0.2 0.50
```


Every minute the output (weight and fee grouped by postCode) is rendered into the console.

Sample output: 
```
08801 15.960 7.00
08079 5.500 2.50
09300 3.200 2.00
90005 2.000 1.50
```

## Build

### Prerequisites
- Java 11 or newer
- Maven

In terminal, go to root of the project and run `mvn clean package`,
compiled binaries are then in target folder.

## Run

### Prerequisites
- Java 11

In project root, run in terminal `java -jar target/bsc-packages-jan-stastny-0.0.1-SNAPSHOT.jar`

You can optionally specify path to a file containing predefine package deliveries records as first argument 
`java -jar target/bsc-packages-jan-stastny-0.0.1-SNAPSHOT.jar src/test/resources/packages.txt`
or moreover add list of fees by package weight 
`java -jar target/bsc-packages-jan-stastny-0.0.1-SNAPSHOT.jar src/test/resources/packages.txt,src/test/resources/fees.txt`