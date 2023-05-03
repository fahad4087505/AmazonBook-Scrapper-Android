package com.chaquo.myapplication

//import io.github.bonigarcia.wdm.WebDriverManager
//import io.github.bonigarcia.wdm.managers.ChromeDriverManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
        val module = py.getModule("amazon_scrap")
// Call the "get_books()" function in the "book_scraper" module
//        val books = module.callAttr("get_books")

        val url = "https://www.amazon.com/some-product"

//        val layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager

        // Call the get_books() function and get the result as a list of Book objects
//        val books = module.callAttr("get_books").toJava(Book::class.java)
//        val amazonScraperInstance = books
//        val result: PyObject = module.callAttr("get_books", url)
//        val amazonScraperModule = py.getModule("python")
//        val amazonScraperClass = amazonScraperModule.callAttr("Amazon_Scraper")

// Call a method on the Amazon_Scraper class and pass the URL as an argument
//        val url = "https://www.amazon.com/some-product"
//        val result: PyObject = amazonScraperClass.callAttr("get_books", url)
//        Log.e("results",result.toString())
        // Create an instance of BookAdapter with the list of books
//        bookAdapter = BookAdapter(listOf(books))

        // Set the adapter for the RecyclerView
//        recyclerView.adapter = bookAdapter
        findViewById<Button>(R.id.button).setOnClickListener {
            try {
//                Call the class data_collection.py for fetch the data

                fetchDataFromHtmlResponseUsingBookUrl()
            } catch (e: PyException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun fetchData() {
        // URL of the Amazon webpage
        val url = "https://www.amazon.com/Animal-Farm-George-Orwell/dp/0451526341"

        // Initialize the Python interpreter
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        // Call the Python function to scrape the data
        val python = Python.getInstance()
        val scrapingModule = python.getModule("data")
        val result = scrapingModule.callAttr("scrape_amazon", url).asMap()

        // Extract the data from the dictionary
//        val title = result["title"].toString()
//        val author = result["author"].toString()
//        val price = result["price"].toString()

        // Print the extracted data
        println("Title: $result")
//        println("Author: $author")
//        println("Price: $price")
    }

    private fun fetchDataFromHtmlResponseUsingBookUrl(){
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val bookUrl = "https://www.amazon.com/Animal-Farm-George-Orwell/dp/0451526341"
//        val bookUrl = "https://www.amazon.com/1984-Signet-Classics-George-Orwell/dp/0451524934/ref=d_bmx_dp_3ct2fe5n_sccl_2_2/132-7015650-1030413?pd_rd_w=ZRFrR&content-id=amzn1.sym.e3b35db1-c427-46b1-86ca-67bddaf5745a&pf_rd_p=e3b35db1-c427-46b1-86ca-67bddaf5745a&pf_rd_r=N0TQHRS47GQBE6M3B3KA&pd_rd_wg=sQSop&pd_rd_r=5ed9962c-de65-4d57-b944-964d2165c37d&pd_rd_i=0451524934&psc=1" // Replace with the actual book URL
        val pyt = Python.getInstance()
        val bookData = pyt.getModule("data_collection").callAttr("scrape_book_data", bookUrl)
        Thread.sleep(7000)
        Log.e("myAndroidClass",bookData.toString())
    }

    private fun fetchAmazonDataResponseUsingBookUrlChromeDriver(){
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(applicationContext))
        }
        val py = Python.getInstance()

// Call the Python class with required arguments
        val numBooks = 10
        val keyword = "Python"
        val book = py.getModule("my_python_script").callAttr("Books", numBooks, keyword)
        // Access and interact with the Python class object
        val numBooksResult = book.get("num_books")?.toInt()
        val keywordResult = book.get("keyword").toString()

// Print the results
        println("Number of Books: $numBooksResult")
        println("Keyword: $keywordResult")
//                val driver: AndroidDriver = AndroidDriver()

// Use the AndroidDriver to interact with a web page
//                driver.get("https://www.example.com")

//                Call the Data Fetch from amazonscrap.py using Chromium Driver.exe
        val pythonFolderPath =
            applicationContext.filesDir.path + "/src/main/python/" // Replace with the actual path to the Python folder in your app

        val chromedriverPath: String =
            File(pythonFolderPath, "chromedriver.exe").getAbsolutePath()
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        val chromedriverFile = File(chromedriverPath)
        chromedriverFile.setReadable(true, false)
        val books = py.getModule("amazon_scrap").callAttr("Amazon_Scraper", numBooks, keyword, chromedriverPath, 15, false)
        val url = "https://www.amazon.com/some-product"
        Log.e("myAndroidClass",books.callAttr("set_driver",url).toString())
        Log.e("myAndroidClass",books.callAttr("get_books").callAttr(url).toString())
        Log.e("myAndroidClass",books.toString())
    }
}