README for chalk and dust
==========================

Steps to configure web client on Windows operating system

- Go to 192.168.100.100\Jitendra\PropCo V3 Contents and copy node.js setup (node- v0.12.4-x64 ) for windows 64 bit. If you are using some other operating system then you can download the installer from         	https://nodejs.org/download/. Choose the installation directory and click finish to complete the installation of node.js on your machine.

- Now open command prompt and change directory to the path on which node.js has been installed in the previous step and run this command -> npm –g  install gulp karma bower. After successful completion of   	the command close the command prompt.

- Now open command prompt and go the path on which node.js has been installed and run this command -> npm  install. After successful completion of the command close the command prompt.

- Now run this command on your command prompt -> git –version. If git is not installed then you can copy the installer from 192.168.100.100\Jitendra\PropCo V3 Contents for windows 64 bit otherwise you can   	install it from https://git-scm.com/downloads
	While installing git please choose -> Run git from windows commmand promt option  on Adjusting your PATH environment screen.                                                     

 -After successful completion of the command close the command prompt again.

- Now open command prompt and change directory to the path of the propcoenterprise-web-client module for example (D:\git\propco-enterprise\propco-web-client), then run this command ->  bower install.

========================================================================================================================================================================================================

Steps to configure web client on Linux operating system

- Go to Terminal and execute the command -> sudo apt-get install npm. 
	This will install nodejs and npm to your machine. If you use ubuntu operating system , run this command also sudo apt-get install nodejs legacy npm. This is required because in Debian based 	distributions ,there is a name clash with another utility called node. So installing nodejs legacy package will rename node to nodejs.


- Verify installations of nodejs, node and npm by checking their versions through following commands:

   nodejs -v
   node -v
   npm -v

   You'll get results like v0.10.25 and 1.3.10.

- Go to your web client module in your project , make sure package.json file is present there and run this command sudo npm install. On succussful completion you'll see node_modules folder at the same 	location. Now run this command -> npm -g install gulp karma bower. Verify installations of gulp by typing ->gulp -v command in terminal.

- Now check your git version by this command git –version . And if git is not installed in your machine do install it by this command -> sudo apt-get install git

- Now search for .bowerrc file in your eclipse and add this at the end of the file :

   "proxy":"http://192.168.100.100:3128",
   "https-proxy":"http://192.168.100.100:3128"

- Now run this command in terminal → bower install . On successful installation of bower you'll see libs folder at this path “src/main/webapp/app/libs” . This path has been mentioned in .bowerrc file.



