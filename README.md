# AmazonBook-Scrapper-Android

This is the source code of the app which uses [Chaquopy](https://chaquo.com/chaquopy/) to include Python code and libraries in an Android app.

* The [top-level](https://github.com/chaquo/chaquopy-matplotlib/blob/master/build.gradle) and 
  [module-level](https://github.com/chaquo/chaquopy-matplotlib/blob/master/app/build.gradle) 
  build.gradle files contain the Chaquopy configuration, including installing:
    python {
            pip {
                install "matplotlib"
                install "selenium"
                install "requests"
                install "beautifulsoup4"
            }
        }
  
* The [MainActivity](https://github.com/chaquo/chaquopy-matplotlib/blob/master/app/src/main/java/com/chaquo/myapplication/MainActivity.kt) 
  inputs take the Amazon Book Url and passes them to a Python function scrape_book_data in data_collection.py.
  
* The [Python code](https://github.com/chaquo/chaquopy-matplotlib/blob/master/app/src/main/python/data_collection.py)
  parses the url html and retreive the selected data like productTitle, Author Name and Price.
* The Python function then returns the data like productTitle, Author Name and Price which is currently display in Log.e("myBookData",bookData.toString())
 in fetchDataFromHtmlResponseUsingBookUrl().
