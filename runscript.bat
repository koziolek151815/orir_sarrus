cd src
start rmiregistry
cd com/company

Javac *.java

cd ..
cd ..
start java com.company/Agent 1
start java com.company/Agent 2
start java com.company/Agent 3
start java com.company/Server

java com.company/Client
