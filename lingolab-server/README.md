# Lingolab server

Note: If you are hacking from a Windows 10 Machine, I HIGHLY recommend working with the Ubuntu Subsystem for Windows 10.
Otherwise, I recommend a VM with a Linux distribution. I've written the bootstrap instructions considering a &ast;nix style machine, but I can try to help if you need it to work on Windows.

## Installation 
To get started, please install: 

Windows:
1. JDK: <code> sudo apt install default-jdk </code> <br/>
2. Maven: <code> sudo apt install maven </code> <br/>

Mac:
1. Maven: <code> brew install maven </code> <br/>

Then, add a file named "config.properties" in "lingolab-server/src/main/resources". The file should have one line, which is:
apikey=YOURAPIKEY

## Use

To build, first cd into lingolab-server. <br/>
Then, run: <code> mvn package </code>

To run, first cd into lingolab-server. <br/>
Then, run: <code> java -jar target/lingolab-0.1.0.jar </code> <br/>

Server currently runs on localhost:8080 <br/>

Available API calls: <br/>
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
