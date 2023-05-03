from flask import Flask, jsonify
from selenium import webdriver

app = Flask(__name__)

@app.route('/api/books')
def get_books():
    driver = webdriver.Chrome()
    driver.get('https://www.amazon.com/gp/new-releases/books/')
    books = driver.find_elements_by_xpath("//div[@class='a-section a-spacing-none aok-relative']")
    books_list = []
    for book in books:
        title = book.find_element_by_xpath('.//h3').text
        author = book.find_element_by_xpath('.//span[2]').text
        books_list.append({'title': title, 'author': author})
    driver.quit()
    return jsonify({'books': books_list})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
