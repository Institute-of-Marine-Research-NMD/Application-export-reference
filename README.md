# Application-export-reference
Application for exporting reference data from the S2D database.

# configuration file
The application needs a file called nmd_reference_loader.properties available in the config catalog of the web container.

the file must contain:

jdbc.driver=org.postgresql.Driver  
jdbc.user=  
jdbc.password=  
jdbc.url=jdbc:postgresql://<server>/<database>  


file.location=<output file folder>  
