#include <ESP8266WiFi.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include "RTClib.h"
#include <Adafruit_FeatherOLED.h>
#include <Adafruit_FeatherOLED_WiFi.h>

#define VBATPIN 1  //pin de la batterie


Adafruit_SSD1306 display = Adafruit_SSD1306();
Adafruit_FeatherOLED_WiFi  oled = Adafruit_FeatherOLED_WiFi();
RTC_DS3231 rtc;
char daysOfTheWeek[7][12] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}; // sera utilise lorsqu on implementera les dates pour les alarmes
const char* ssid     = ""; 			  //SSID
const char* password = "";    		  //PSWD
const int port = 5005;                //PORT
const char* host = "80.200.58.20";    //IP HOST
const int vibreur = 14;
const int maxAlarmes = 15;
struct Alarme {
  int heure;
  int minutes;
  String raison;
};
float measuredvbat = analogRead(VBATPIN);
Alarme tabAlarmes[maxAlarmes];
int nbreAlarmes = 0;

void majBatterie() 
{
    int   vbatADC   = 0;         // The raw ADC value off the voltage div
    float vbatFloat = 0.0F;      // The ADC equivalent in millivolts
    float vbatLSB   = 0.80566F;  // mV per LSB
   
    // Read the analog in value:
    vbatADC = analogRead(VBATPIN);
    vbatADC = analogRead(VBATPIN);
   
    // Multiply the ADC by mV per LSB, and then
    // double the output to compensate for the
    // 10K+10K voltage divider
    vbatFloat = ((float)vbatADC * vbatLSB) * 2.0F;
   
    oled.setBattery(vbatFloat/1000);
}
void afficherHeure(DateTime now){
    //display.clearDisplay();
    display.setTextSize(3);
    display.setTextColor(WHITE);
    display.setCursor(10,6);
    display.print(now.hour(), DEC);
    display.print(':');
    display.print(now.minute(), DEC);
    display.display();  
}
void demanderAlarmesTCP(){
    // Creation de la connection TCP
    Serial.print("connecting to ");
    Serial.println(host);
    WiFiClient client;
    if (!client.connect(host, port)) {
      Serial.println("connection failed");
      return;
    }
  
    // si la connection a reussi
    // Envoi d une requete au serveur
    client.print("pret");
    delay(10);
  
    int indiceTab = 0;
    Serial.println("Respond:");
    //Tant qu il y a connection
    while(client.available()){
      if(indiceTab<maxAlarmes){  
        String line = client.readStringUntil(',');
        tabAlarmes[indiceTab].heure=line.toInt();
        Serial.println(tabAlarmes[indiceTab].heure);
        client.read();
        line = client.readStringUntil(',');
        tabAlarmes[indiceTab].minutes=line.toInt();
        Serial.println(tabAlarmes[indiceTab].minutes);
        client.read();
        line = client.readStringUntil(')');
        tabAlarmes[indiceTab].raison=line;
        Serial.println(tabAlarmes[indiceTab].raison);
        client.read();
        client.read();
        
      }else{
        String line = client.readStringUntil('\r'); // au cas ou le nombre d alarme recue depasse le nombre d alarme dispo, on ecoute quand meme le reste
        Serial.println("trop d'alarmes");
        Serial.println(line);
      }
      indiceTab++;
      delay(1000);
    }
    //maj du nombre d alarmes enregistrees
    nbreAlarmes = indiceTab;
    
    Serial.println();
    Serial.println("closing connection");
}

void afficherString(String texte, int taille){
    display.clearDisplay();
    display.setTextSize(taille);
    display.setTextColor(WHITE);
    display.setCursor(10,3);
    display.print(texte);
    display.display();  
}
void afficherImage(const unsigned char img []){
    display.clearDisplay();
    display.setCursor(0,0);
    display.drawBitmap( 0, 0, img, 124, 31, 1);
    display.display();
}
void init_oled(){
    oled.init();
    //oled.clearDisplay();
    majBatterie();
    oled.setRSSIVisible(false);
    oled.setConnectedVisible (false);
    oled.setBatteryIcon(true);
    oled.setBatteryVisible(true);
    oled.refreshIcons();
    oled.display(); 
}

void afficherBatterie(){
    majBatterie();
    oled.refreshIcons();
    oled.display(); 
}

//constante qui contient l image du verre
const unsigned char PROGMEM avertEau [] = {
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x1F, 0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x7F, 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xF0, 0x7C, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x01, 0xC7, 0x9E, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x03, 0x8F, 0x8E, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x03, 0x0F, 0x87, 0x00, 0x61, 0x80, 0x00, 0x00, 0x00, 0x00, 0x01, 0x98, 0x00, 0x00, 0x00, 0xF0,
    0x07, 0x07, 0x03, 0x00, 0x61, 0x80, 0x00, 0x00, 0x00, 0x00, 0x01, 0x98, 0x00, 0x00, 0x01, 0x98,
    0x06, 0x00, 0x03, 0x80, 0x61, 0x80, 0x00, 0x00, 0x00, 0x00, 0x01, 0x98, 0x00, 0x00, 0x01, 0x98,
    0x06, 0x07, 0x81, 0x80, 0x61, 0x9F, 0x01, 0xF0, 0xF1, 0x98, 0x0F, 0x98, 0xF0, 0xF1, 0x98, 0x18,
    0x06, 0x07, 0x81, 0x80, 0x61, 0x99, 0x81, 0x99, 0x99, 0x98, 0x19, 0x81, 0x99, 0x99, 0x98, 0x30,
    0x06, 0x07, 0x81, 0x80, 0x61, 0x99, 0x81, 0x99, 0xF9, 0x98, 0x19, 0x81, 0xF8, 0x79, 0x98, 0x60,
    0x06, 0x07, 0x81, 0x80, 0x61, 0x99, 0x81, 0x99, 0x81, 0x98, 0x19, 0x81, 0x80, 0xD9, 0x98, 0x60,
    0x06, 0x07, 0x83, 0x80, 0x61, 0x99, 0x81, 0x99, 0x81, 0x98, 0x19, 0x81, 0x81, 0x99, 0x98, 0x00,
    0x07, 0x07, 0x83, 0x00, 0x33, 0x19, 0x81, 0x99, 0x99, 0x98, 0x19, 0x81, 0x99, 0x99, 0x98, 0x60,
    0x03, 0x07, 0x87, 0x00, 0x1E, 0x19, 0x81, 0xF0, 0xF0, 0xF8, 0x0F, 0x80, 0xF0, 0xF8, 0xF8, 0x60,
    0x03, 0x87, 0x8E, 0x00, 0x00, 0x00, 0x01, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x01, 0xC7, 0x9E, 0x00, 0x00, 0x00, 0x01, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xF0, 0x7C, 0x00, 0x00, 0x00, 0x01, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x7F, 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x1F, 0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
};

void setup() {
    Serial.begin(115200);
    delay(10);
  
    //initialisation de la table des alarmes
    for( int i=0; i <  maxAlarmes; i++){
      tabAlarmes[i].heure=25;
      tabAlarmes[i].minutes=61;
      tabAlarmes[i].raison="";
    }
    
    // initialisation du oled 128x32
    display.begin(SSD1306_SWITCHCAPVCC, 0x3C);  
    delay(1000);
    display.clearDisplay();
    display.display();
    display.setTextSize(1);
    display.setTextColor(WHITE);
    display.setCursor(0,0);
  
    //initialisation du vibreur
    pinMode(vibreur, OUTPUT);
    digitalWrite(vibreur, LOW);
    
    //Verifie que le RTC est bien connecte
    if (! rtc.begin()) {
      Serial.println("Couldn't find RTC");
      while (1);
    }
    
    //initialise l heure au cas ou cela n a pas ete fait
    if (rtc.lostPower()) {
      Serial.println("Reinitialisation du RTC");
      // ajuste le temps a l heure de la compilation
      rtc.adjust(DateTime(F(__DATE__), F(__TIME__)));
      // Pour reajuster la date et l heure :
      // rtc.adjust(DateTime(annee, mois, jour, heure, minute, sec));
    }
  
    //Connection au WiFi et affichage sur l ecran
    Serial.println();
    display.print("Connecting to ");
    Serial.print("Connecting to ");
    Serial.println(ssid);
    display.println(ssid);
    display.display();
    WiFi.begin(ssid, password);
    //Tant que le WiFi n est pas connecte on affiche des points
    int sortie=0;
    while (WiFi.status() != WL_CONNECTED && sortie<15) {
      delay(500);
      Serial.print(".");
      sortie++;
    }
    if(WiFi.status() != WL_CONNECTED){
      afficherString("La connection a échoué",2);
    }else{
      display.clearDisplay();
      display.display(); 
      //affichage de la reussite de connection 
      Serial.println("");
      Serial.println("WiFi connected");
      display.println("WiFi connected");
      Serial.println("IP address: ");
      display.println(WiFi.localIP());
      Serial.println(WiFi.localIP());
      display.display();  
    }
    
    delay(2000);
    
    //initialisation du oled pour l affichage de la batterie
    init_oled();
}


//variables pour l affichage de l heure
//on sotcke l heure afin de ne rafraichir l ecran que quand l heure a changee
int hour = 0;
int minute = 0;



void loop() {
    DateTime now = rtc.now();
    delay(1000);
  
    //parcours de la table des alarmes
    for( int i=0; i <  nbreAlarmes; i++){
      if(tabAlarmes[i].heure == now.hour() && tabAlarmes[i].minutes == now.minute()){  //si une alarme correspond a l heure actuelle
        if(tabAlarmes[i].raison == "eau"){                                             //si la rasion de l alarme est de prendre de l eau
          afficherImage(avertEau);                                                     //on affiche alors une message pour proposer de boire de l eau 
        }
        else{
          afficherString(tabAlarmes[i].raison,2);                                      //sinon on affiche la raison
        }
        digitalWrite(vibreur, HIGH);                                                   //on fait également vibrer
        delay(5000);
        digitalWrite(vibreur, LOW);
        display.clearDisplay();
        afficherBatterie();
        afficherHeure(now);
        delay(55000);
      }
    }
    
    
    //on rafraichi l ecran si l heure a changee (+1 min)
    if(hour!=now.hour()||minute!=now.minute()){
      display.clearDisplay();
      afficherBatterie();
      afficherHeure(now);
      hour=now.hour();
      minute=now.minute();
    }
  
    //on demande au serveur si une maj d alarmes est disponible
    demanderAlarmesTCP();
  
    
    afficherHeure(now);
}
