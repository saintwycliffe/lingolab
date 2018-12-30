TODO: Make this look readable

Note: If you are hacking from a Windows 10 Machine, I HIGHLY recommend working with the Ubuntu Subsystem for Windows 10.
Otherwise, I recommend a VM with a Linux distribution. I've written the bootstrap instructions considering a &ast;nix style machine, but I can try to help if you need it to work on Windows.

To get started, please install:
JDK: sudo apt install default-jdk
Maven: sudo apt install maven

Then, add a file named "config.properties" in "lingolab-server/src/main/resources". The file should have one line, which is:
apikey=YOURAPIKEY

To build, first cd into lingolab-server.
Then, run: mvn package

To run, first cd into lingolab-server.
Then, run: java -jar target/lingolab-0.1.0.jar

Server currently runs on localhost:8080
Available API calls:
- /greeting -> returns a JSON object with "Hello, World!"
- /greeting?name=MyName -> returns a JSON object with "Hello, MyName!", conforms to input
- /translate?name=MyName&lang=MyLang -> returns a JSON object of the form:
{
  originalName: "MyName"
  nativeLanguage: "MyLang"
  translations: [{
    language: "OtherLang",
    translation: "TranslatedName"
  }]
}