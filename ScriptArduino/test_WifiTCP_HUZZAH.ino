#include <ESP8266WiFi.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>

Adafruit_SSD1306 display = Adafruit_SSD1306();
const char* ssid     = "VOO-541874";
const char* password = "EGSAKUFH";

const char* host = "109.133.170.117";

void setup() {
  Serial.begin(115200);
  delay(10);

  display.begin(SSD1306_SWITCHCAPVCC, 0x3C);  // initialize with the I2C addr 0x3C (for the 128x32)
  delay(1000);
 
  // Clear the buffer.
  display.clearDisplay();
  display.display();
  display.setTextSize(1);
  display.setTextColor(WHITE);
  display.setCursor(0,0);
  
  // We start by connecting to a WiFi network
  Serial.println();
  display.print("Connecting to ");
  Serial.print("Connecting to ");
  Serial.println(ssid);
  display.println(ssid);
  display.display();

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }


  Serial.println("");
  Serial.println("WiFi connected");
  display.println("WiFi connected");
  Serial.println("IP address: ");
  display.println(WiFi.localIP());
  Serial.println(WiFi.localIP());
  display.display();  
}

int value = 0;

void loop() {
  delay(5000);
  ++value;

  Serial.print("connecting to ");
  Serial.println(host);

  // Use WiFiClient class to create TCP connections
  WiFiClient client;
  const int port = 5005;
  if (!client.connect(host, port)) {
    Serial.println("connection failed");
    return;
  }

  Serial.print("Requesting URL: ");
  Serial.println(url);

  // This will send the request to the server
  client.print("salut donne moi des infos");
  delay(10);

  // Read all the lines of the reply from server and print them to Serial
  Serial.println("Respond:");
  display.clearDisplay();
  display.display();
  display.setCursor(0,0);
  while(client.available()){
    String line = client.readStringUntil('\r');
    Serial.print(line);
    display.println(line);
    display.display();
  }

  Serial.println();
  Serial.println("closing connection");
}
