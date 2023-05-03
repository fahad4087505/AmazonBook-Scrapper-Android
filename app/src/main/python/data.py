import requests
from bs4 import BeautifulSoup

def scrape_amazon(url):
    # Send a GET request to the URL and store the response in a variable
    response = requests.get(url)

    # Create a Beautiful Soup object to parse the HTML content
    soup = BeautifulSoup(response.content, 'html.parser')

    # Extract the product title
    title = soup.find('span', {'id': 'productTitle'}).get_text().strip()

    # Extract the author
    author = soup.find('a', {'class': 'a-link-normal', 'href': '/s?k=&i=stripbooks&field-author=George+Orwell'}).get_text().strip()

    # Extract the price
    price = soup.find('span', {'id': 'newBuyBoxPrice'}).get_text().strip()

    # Return the extracted data as a dictionary
    return {'title': title, 'author': author, 'price': price}