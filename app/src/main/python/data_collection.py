import requests
from bs4 import BeautifulSoup
def scrape_book_data(url):
    response = requests.get(url)
    html_content = response.content

    soup = BeautifulSoup(html_content, 'html.parser')

    # Extract book title
    title_element = soup.find('span', {'id': 'productTitle'})
    title = title_element.text.strip() if title_element else None

    # Extract book author
    author_element = soup.find('span', {'class': 'author'})
    author = author_element.text.strip() if author_element else None

    # Extract book price
    price_element = soup.find('span', {'class': 'a-price-whole'})
    price = price_element.text.strip() if price_element else None

    # Return scraped data as a dictionary
    book_data = {
        'title': title,
        'author': author,
        'price': price
    }

    return book_data


