@echo off
:: Represents the path to the Java XJC executable
SET xjc= "C:\Users\user\.jdks\corretto-1.8.0_312\bin\xjc.exe"

:: Represents the package under which the classes will be generated
SET packageName="grade"

:: Represents the path to the XSD schema based on which the classes will be generated
SET schema="D:\Data\Facultate\Master\An 1\SEM 1\MSIC\Proiect\StudentManagement\src\main\resources\view\xsd_schemas\Nota.xsd"

:: Represents the folder in which the classes will be generated
SET OUTPUT_FOLDER="D:\Data\Facultate\Master\An 1\SEM 1\MSIC\Proiect\StudentManagement\src\main\java\com\ace\ucv\model\xml"


%xjc% -xmlschema %schema% -d %OUTPUT_FOLDER% -p %packageName%
pause