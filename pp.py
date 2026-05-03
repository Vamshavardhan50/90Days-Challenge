import phonenumbers
from phonenumbers import geocoder, carrier

# Enter phone number with country code (e.g., +91)
number = "+9170389 66396" 
ch_number = phonenumbers.parse(number, "CH")

# Get Location
print(geocoder.description_for_number(ch_number, "en"))
# Get Service Provider
print(carrier.name_for_number(ch_number, "en"))
