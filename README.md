# QRCode-API
API that generates QRCodes

## Good To Know

#### Default-Login:
- user:password

## Generate QRCodes

### Text
```
localhost:8080/gen?type=text&&data=Hello World
```
### URL
```
localhost:8080/gen?type=text&&data=https://github.com
```

### WIFI Informations
```
localhost:8080/gen?type=wifi&&auth=WPA&&ssid=NETWORKNAME&&pw=PASSWORD&&hidden=false
```

### VCard
```
localhost:8080/gen?type=vcard&&name=NAME&&email=EMAIL&&address=STREET&&company=COMPANY&&tel=PHONE&&web=WEBSITE
```

### Geo Location
```
localhost:8080/gen?type=geo&&lat=48.772707&&lon=9.176515
```


## Build With

* [Java 8](https://www.java.com/de/) - Java
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://github.com/spring-projects/spring-boot) - Framework
* [QRCode Generator](https://github.com/kenglxn/QRGen) - QRCode generation api for java built on top ZXING

## License
This project is licensed under the GNU License - see the [LICENSE.md](LICENSE.md) file for details
