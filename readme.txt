Realm Relay v1.1.0 README
________________________________________________________________
WINDOWS

Installation:
	Extract the .zip file anywhere
	Open "C:\Windows\System32\drivers\etc\hosts" with notepad in administrator mode
	Add a new line to the file, with the following text:
		127.0.0.1 ec2-50-19-47-160.compute-1.amazonaws.com
	Save the hosts file
		
Usage:
	Start the program by double-clicking "win_start.bat"
________________________________________________________________
MAC

Installation:
	Extract the .zip file anywhere
	Open Terminal.app
	Type the following text to open the hosts file:
		sudo nano /private/etc/hosts
	Add a new line to the file, with the following text:
		127.0.0.1 ec2-50-19-47-160.compute-1.amazonaws.com
	Save the hosts file
		
Usage:
	Open Terminal.app
	Type the following text, with the correct filepath replaced:
		sudo java -jar "path/to/rotmg.jar"
		
________________________________________________________________