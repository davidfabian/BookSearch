package com.example.d.booksearch;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */

public final class QueryUtils {

    private static final String GBAPI_URL = "https://www.googleapis.com/books/v1/volumes?q=magyar&maxResults=10";
    //modify this according to API documentation for different results
    private static final String JSONDUMMY = "{\n" +
            " \"kind\": \"books#volumes\",\n" +
            " \"totalItems\": 431,\n" +
            " \"items\": [\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"37oAAAAAcAAJ\",\n" +
            "   \"etag\": \"G9yAkUXGZwg\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/37oAAAAAcAAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"The City of the Magyar, Or Hungary and Her Institutions in 1839 - 40\",\n" +
            "    \"subtitle\": \"In Three Vols\",\n" +
            "    \"authors\": [\n" +
            "     \"Julia Pardoe\"\n" +
            "    ],\n" +
            "    \"publishedDate\": \"1840\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"OTHER\",\n" +
            "      \"identifier\": \"BSB:BSB10010838\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 431,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"0.0.1.0.full.1\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=37oAAAAAcAAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=37oAAAAAcAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=37oAAAAAcAAJ&printsec=frontcover&dq=magyar&hl=&cd=1&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=37oAAAAAcAAJ&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-37oAAAAAcAAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"FREE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=37oAAAAAcAAJ&rdid=book-37oAAAAAcAAJ&rdot=1&source=gbs_api\"\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"ALL_PAGES\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": true,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false,\n" +
            "     \"downloadLink\": \"http://books.google.com.kw/books/download/The_City_of_the_Magyar_Or_Hungary_and_He.epub?id=37oAAAAAcAAJ&hl=&output=epub&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"downloadLink\": \"http://books.google.com.kw/books/download/The_City_of_the_Magyar_Or_Hungary_and_He.pdf?id=37oAAAAAcAAJ&hl=&output=pdf&sig=ACfU3U2yHRy2_B9YyvonHyv2I2FJNb0S9w&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=37oAAAAAcAAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"FULL_PUBLIC_DOMAIN\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"gugDAAAAQAAJ\",\n" +
            "   \"etag\": \"+MWg6nOQEw4\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/gugDAAAAQAAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"The city of the Magyar, or Hungary and her institutions in 1839-40\",\n" +
            "    \"authors\": [\n" +
            "     \"Julia S H. Pardoe\"\n" +
            "    ],\n" +
            "    \"publishedDate\": \"1840\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"OTHER\",\n" +
            "      \"identifier\": \"OXFORD:555005474\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"full-1.0.0\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=gugDAAAAQAAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=gugDAAAAQAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=gugDAAAAQAAJ&printsec=frontcover&dq=magyar&hl=&cd=2&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=gugDAAAAQAAJ&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-gugDAAAAQAAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"FREE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=gugDAAAAQAAJ&rdid=book-gugDAAAAQAAJ&rdot=1&source=gbs_api\"\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"ALL_PAGES\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": true,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false,\n" +
            "     \"downloadLink\": \"http://books.google.com.kw/books/download/The_city_of_the_Magyar_or_Hungary_and_he.epub?id=gugDAAAAQAAJ&hl=&output=epub&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"downloadLink\": \"http://books.google.com.kw/books/download/The_city_of_the_Magyar_or_Hungary_and_he.pdf?id=gugDAAAAQAAJ&hl=&output=pdf&sig=ACfU3U2A-3xn9IF2dHV4hb5CMgpEFaAQwg&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=gugDAAAAQAAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"FULL_PUBLIC_DOMAIN\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"wtuvCwAAQBAJ\",\n" +
            "   \"etag\": \"RazTkDI5mP0\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/wtuvCwAAQBAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Magyar Women\",\n" +
            "    \"subtitle\": \"Hungarian Women’s Lives, 1960s–1990s\",\n" +
            "    \"authors\": [\n" +
            "     \"Chris Corrin\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Springer\",\n" +
            "    \"publishedDate\": \"1994-02-12\",\n" +
            "    \"description\": \"Vast changes within East and Central Europe since 1989 have brought countries in this region, including Hungary, under sharp focus. This important study of women's situation within the changing context of Hungarian society gives a comprehensive overview of the various factors which make up women's lives. Rather than experiencing social radicalism in the 1960s, women in Hungary were experiencing the full effects of their rigid, authoritarian statist policies. What this meant for their everyday lives is considered in terms of women's paid and unpaid work, family ideologies, social policy innovations, women's health care, changing attitudes, and women's hopes and aspirations. Against the background of new openings on the political scene, questions concerning civil society and space for women's agendas are vital.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781349231263\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1349231266\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 312,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Social Science\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"preview-1.0.0\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=wtuvCwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=wtuvCwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=wtuvCwAAQBAJ&pg=PA281&dq=magyar&hl=&cd=3&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=wtuvCwAAQBAJ&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-wtuvCwAAQBAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"FOR_SALE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"listPrice\": {\n" +
            "     \"amount\": 59.92,\n" +
            "     \"currencyCode\": \"USD\"\n" +
            "    },\n" +
            "    \"retailPrice\": {\n" +
            "     \"amount\": 59.92,\n" +
            "     \"currencyCode\": \"USD\"\n" +
            "    },\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=wtuvCwAAQBAJ&rdid=book-wtuvCwAAQBAJ&rdot=1&source=gbs_api\",\n" +
            "    \"offers\": [\n" +
            "     {\n" +
            "      \"finskyOfferType\": 1,\n" +
            "      \"listPrice\": {\n" +
            "       \"amountInMicros\": 5.992E7,\n" +
            "       \"currencyCode\": \"USD\"\n" +
            "      },\n" +
            "      \"retailPrice\": {\n" +
            "       \"amountInMicros\": 5.992E7,\n" +
            "       \"currencyCode\": \"USD\"\n" +
            "      }\n" +
            "     }\n" +
            "    ]\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED_FOR_ACCESSIBILITY\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.kw/books/download/Magyar_Women-sample-pdf.acsm?id=wtuvCwAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=wtuvCwAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"TAMAS, Pál, Hova Lett a \\u003cb\\u003eMagyar\\u003c/b\\u003e Feminizmus? (Where has Hungarian feminism \\u003cbr\\u003e\\ngone?) Elet és Irodalom (Life and Literature), 1 May 1987, p. 5. Books CSEH-\\u003cbr\\u003e\\nSZOMBATHY, László, A Mai \\u003cb\\u003eMagyar\\u003c/b\\u003e Család (The contemporary Hungarian \\u003cbr\\u003e\\nfamily),&nbsp;...\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"l2NaBAAAQBAJ\",\n" +
            "   \"etag\": \"h3LqpLkA5F0\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/l2NaBAAAQBAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Magyar Örökség Washingtonban - Hungarian Heritage: Roots to Revival\",\n" +
            "    \"subtitle\": \"The Hungarian Program of the 2013 Smithsonian Folklife Festival, Washington, D.C.\",\n" +
            "    \"authors\": [\n" +
            "     \"Fülemile Ágnes\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Balassi Intézet\",\n" +
            "    \"publishedDate\": \"2014-08-19\",\n" +
            "    \"description\": \"Magyarország 2013 nyarán az Egyesült Államok legnagyobb szabású szabadtéri rendezvényének, a Washington szívében évente megrendezett Smithsonian Folklife Festival-nak a vendége volt 10 napon keresztül. In the summer of 2013, Hungary was the guest of honor for 10 days at one of the largest outdoor events in the United States, the annual Smithsonian Folklife Festival. A Hungarian Heritage – Roots to Revival (Magyar Örökség – A gyökerektől az újjászületésig) címet viselőprogram Magyarország népművészeti hagyományait mutatta be a zene, a tánc, a kézművesség, az öltözködés, a gasztronómia terén. Kétszer öt napig, június 26-30. és július 3-7. között mintegy 120 főnyi, zenészekből, táncosokból, kézművesekből, szakácsokból, kutatókból, játékmesterekből, pedagógusokból és hagyományőrző közösségek képviselőiből álló magyar delegáció élettel telítette meg az USA nemzeti terének a National Mall-nak füvére épített magyar falut. A szereplők a teljes magyar nyelvterületről, határon innen és túlról, valamint Észak-Amerikából érkeztek. A most megjelent Magyar Örökség Washingtonban című könyv ennek a fesztiválszereplésnek állít emléket. Belátást enged a részletekbe, az előkészítés, szervezés folyamatába, a döntési helyzetekbe, a koncepcionális elképzelésekbe, a feladat összetettségébe, a csapatmunkába. --- Titled Hungarian Heritage – Roots to Revival, the program displayed Hungary’s folk art traditions in music, dance, crafts, dress and gastronomy. For the 10 days of the festival, June 26–30 and July 3–7, the Hungarian Village on the National Mall was filled with some 120 delegates of musicians, dancers, artisans, cooks, researchers, folk games experts, educators, and representatives of tradition bearing communities. The performers came from all over the Hungarian speaking areas, from within and beyond Hungary’s borders, as well as North America.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9789635670482\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"9635670486\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 207,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Ethnology\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": true,\n" +
            "    \"contentVersion\": \"preview-1.0.0\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=l2NaBAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=l2NaBAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=l2NaBAAAQBAJ&pg=PA170&dq=magyar&hl=&cd=4&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.kw/books?id=l2NaBAAAQBAJ&dq=magyar&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Magyar_%C3%96r%C3%B6ks%C3%A9g_Washingtonban_Hungaria.html?hl=&id=l2NaBAAAQBAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"ALL_PAGES\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.kw/books/download/Magyar_%C3%96r%C3%B6ks%C3%A9g_Washingtonban_Hungaria-sample-pdf.acsm?id=l2NaBAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=l2NaBAAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Az, hogy a \\u003cb\\u003emagyar\\u003c/b\\u003e program elérte, hogy legyen alkoholfogyasztásra alkalmas \\u003cbr\\u003e\\nkiszolgáló egysége (borsátra) ritka privilégiumnak számított. Ez az eredmény a \\u003cbr\\u003e\\nszakértôkkel segített, idôben elindított körültekintô szervezésnek volt köszönhetô,\\u003cbr\\u003e\\n&nbsp;...\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"TP4sDwAAQBAJ\",\n" +
            "   \"etag\": \"u65vfvI1qGk\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/TP4sDwAAQBAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Magyar Warriors\",\n" +
            "    \"authors\": [\n" +
            "     \"Dénes Bernád\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Helion & Company\",\n" +
            "    \"publishedDate\": \"2015-02-19\",\n" +
            "    \"description\": \"The Hungarian armed forces (known as the Honvédség) were built up in the 1930s, their expansion gaining momentum once Hungary became free of the strict post-WWI Trianon treaty limitations in August 1938. Politically, Hungary was looking for a strong ally, who would help it to recover at least some of the territories containing sizable Magyar ethnic populations that had been lost after the First World War. Initially, in the mid-1930s, Italy gave political assistance and supplied military material, then - on the eve of WWII - Germany also lent support. In November 1938, Hungary managed to peacefully recover a chunk of its former territory from Czechoslovakia, followed by the Sub-Carpathian area during a brief border war in March 1939, then the northern part of Transylvania from Rumania in August 1940. Later, in April 1941, the Bachka region and parts of Baranya were also taken back from the dismembered Yugoslavia, in a swift military action. The rub was that Hungary was sucked into the cauldron of the Eastern front, and soon the Honvéds (Hungarian soldiers) found themselves deep in Soviet territory, outgunned and outnumbered by the Red Army. Later on, from August 1944, the beleaguered Honvédség had to fight against the mighty Soviet army in defense of its own territory. Alongside tiny Croatia, Hungary remained the last German ally up to the bitter end. This comprehensive reference, to be published in two volumes, and the fruit of over twenty years of meticulous research, strives to provide a complete picture of the Hungarian armed forces between the years 1919-1945. It starts with a brief history of the Magyars, describes the political situation in Hungary before and during WWII, the building of the armed forces, the growth of domestic arms manufacturers, the organization of the armed forces units and how they changed during the war. The various campaigns of the war are described in great detail, illustrated with many photographs and maps. This, the first volume, contains approximately 550 photographs, many previously unpublished, as well as numerous tables and maps of the various campaigns. The authors drew on official Hungarian and German archives, and a multitude of private sources, both from individuals living in Hungary and Hungarian émigrés from the Western Diaspora. The result of this Herculean effort is a two-volume series destined to be the reference work on the topic, a must for people fascinated by military history, or generally interested in the 1100-year-long rich history of Hungary and its Magyar Warriors. Volume 2, to be published in 2011, will cover all small arms, artillery, soft-skin and armored vehicles, motorcycles, as well as aircraft, the insignia, markings and camouflage of armored vehicles and aircraft, both of Hungarian indigenous design and those supplied by Germany and Italy, complete with technical data, production and delivery figures. An extensive selection of b/w photographs and color plates will be included.The Hungarian armed forces (known as the Honvédség) were built up in the 1930s, their expansion gaining momentum once Hungary became free of the strict post-WWI Trianon treaty limitations in August 1938. Politically, Hungary was looking for a strong ally, who would help it to recover at least some of the territories containing sizable Magyar ethnic populations that had been lost after the First World War. Initially, in the mid-1930s, Italy gave political assistance and supplied military material, then - on the eve of WWII - Germany also lent support. In November 1938, Hungary managed to peacefully recover a chunk of its former territory from Czechoslovakia, followed by the Sub-Carpathian area during a brief border war in March 1939, then the northern part of Transylvania from Rumania in August 1940. Later, in April 1941, the Bachka region and parts of Baranya were also taken back from the dismembered Yugoslavia, in a swift military action. The rub was that Hungary was sucked into the cauldron of the Eastern front, and soon the Honvéds (Hungarian soldiers) found themselves deep in Soviet territory, outgunned and outnumbered by the Red Army. Later on, from August 1944, the beleaguered Honvédség had to fight against the mighty Soviet army in defense of its own territory. Alongside tiny Croatia, Hungary remained the last German ally up to the bitter end. This comprehensive reference, to be published in two volumes, and the fruit of over twenty years of meticulous research, strives to provide a complete picture of the Hungarian armed forces between the years 1919-1945. It starts with a brief history of the Magyars, describes the political situation in Hungary before and during WWII, the building of the armed forces, the growth of domestic arms manufacturers, the organization of the armed forces units and how they changed during the war. The various campaigns of the war are described in great detail, illustrated with many photographs and maps. This, the first volume, contains approximately 550 photographs, many previously unpublished, as well as numerous tables and maps of the various campaigns. The authors drew on official Hungarian and German archives, and a multitude of private sources, both from individuals living in Hungary and Hungarian émigrés from the Western Diaspora. The result of this Herculean effort is a two-volume series destined to be the reference work on the topic, a must for people fascinated by military history, or generally interested in the 1100-year-long rich history of Hungary and its Magyar Warriors. Volume 2, to be published in 2011, will cover all small arms, artillery, soft-skin and armored vehicles, motorcycles, as well as aircraft, the insignia, markings and camouflage of armored vehicles and aircraft, both of Hungarian indigenous design and those supplied by Germany and Italy, complete with technical data, production and delivery figures. An extensive selection of b/w photographs and color plates will be included.The Hungarian armed forces (known as the Honvédség) were built up in the 1930s, their expansion gaining momentum once Hungary became free of the strict post-WWI Trianon treaty limitations in August 1938. Politically, Hungary was looking for a strong ally, who would help it to recover at least some of the territories containing sizable Magyar ethnic populations that had been lost after the First World War. Initially, in the mid-1930s, Italy gave political assistance and supplied military material, then - on the eve of WWII - Germany also lent support. In November 1938, Hungary managed to peacefully recover a chunk of its former territory from Czechoslovakia, followed by the Sub-Carpathian area during a brief border war in March 1939, then the northern part of Transylvania from Rumania in August 1940. Later, in April 1941, the Bachka region and parts of Baranya were also taken back from the dismembered Yugoslavia, in a swift military action. The rub was that Hungary was sucked into the cauldron of the Eastern front, and soon the Honvéds (Hungarian soldiers) found themselves deep in Soviet territory, outgunned and outnumbered by the Red Army. Later on, from August 1944, the beleaguered Honvédség had to fight against the mighty Soviet army in defense of its own territory. Alongside tiny Croatia, Hungary remained the last German ally up to the bitter end. This comprehensive reference, to be published in two volumes, and the fruit of over twenty years of meticulous research, strives to provide a complete picture of the Hungarian armed forces between the years 1919-1945. It starts with a brief history of the Magyars, describes the political situation in Hungary before and during WWII, the building of the armed forces, the growth of domestic arms manufacturers, the organization of the armed forces units and how they changed during the war. The various campaigns of the war are described in great detail, illustrated with many photographs and maps. This, the first volume, contains approximately 550 photographs, many previously unpublished, as well as numerous tables and maps of the various campaigns. The authors drew on official Hungarian and German archives, and a multitude of private sources, both from individuals living in Hungary and Hungarian émigrés from the Western Diaspora. The result of this Herculean effort is a two-volume series destined to be the reference work on the topic, a must for people fascinated by military history, or generally interested in the 1100-year-long rich history of Hungary and its Magyar Warriors. Volume 2, to be published in 2011, will cover all small arms, artillery, soft-skin and armored vehicles, motorcycles, as well as aircraft, the insignia, markings and camouflage of armored vehicles and aircraft, both of Hungarian indigenous design and those supplied by Germany and Italy, complete with technical data, production and delivery figures. An extensive selection of b/w photographs and color plates will be included.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781912174492\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1912174499\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"1.1.1.0.preview.3\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=TP4sDwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=TP4sDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=TP4sDwAAQBAJ&pg=PA138&dq=magyar&hl=&cd=5&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.kw/books?id=TP4sDwAAQBAJ&dq=magyar&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Magyar_Warriors.html?hl=&id=TP4sDwAAQBAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.kw/books/download/Magyar_Warriors-sample-epub.acsm?id=TP4sDwAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=TP4sDwAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"It has to be stressed again that even if most \\u003cb\\u003eMagyar\\u003c/b\\u003e officers continued to serve \\u003cbr\\u003e\\nunder Szálasi&#39;s regime, it does not necessarily mean that they were Nazis or \\u003cbr\\u003e\\nextremists – a notable exception being Colonel General Beregfy, the new \\u003cbr\\u003e\\nMinister of&nbsp;...\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"rqu7CgAAQBAJ\",\n" +
            "   \"etag\": \"0xcvcv0olAk\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/rqu7CgAAQBAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"MAGYAR HEROISM AND THE ARRIVAL OF THE 1956-1957 HUNGARIAN IMMIGRANT REFUGEES TO SOUTH AFRICA\",\n" +
            "    \"authors\": [\n" +
            "     \"Lindsay Tonkin\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Lulu.com\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781326441784\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1326441787\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"0.2.0.0.preview.1\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=rqu7CgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=rqu7CgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=rqu7CgAAQBAJ&pg=PA52&dq=magyar&hl=&cd=6&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.kw/books?id=rqu7CgAAQBAJ&dq=magyar&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/MAGYAR_HEROISM_AND_THE_ARRIVAL_OF_THE_19.html?hl=&id=rqu7CgAAQBAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=rqu7CgAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"The \\u003cb\\u003eMagyar\\u003c/b\\u003e would never forget or ever stop loving. With total faith in the Almighty \\u003cbr\\u003e\\nwho hears and sees everything the \\u003cb\\u003eMagyar\\u003c/b\\u003e had made the supreme sacrifice and \\u003cbr\\u003e\\nin doing so became the catalyst in a chain of events that destroyed a myth and&nbsp;...\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"hdk3KSGiLSsC\",\n" +
            "   \"etag\": \"fVjjMCBAx3I\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/hdk3KSGiLSsC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Magyar, Stars & Stripes\",\n" +
            "    \"authors\": [\n" +
            "     \"Michael Lipiner\"\n" +
            "    ],\n" +
            "    \"publisher\": \"iUniverse\",\n" +
            "    \"publishedDate\": \"2005-05\",\n" +
            "    \"description\": \"Magyar, Stars & Stripes strikingly recounts a Hungarian Jewish family's history of love, affection, persecution, and injustice. It tells about their close kinship and heart-wrenching experiences in labor and concentration camps. The book chronicles Alexander Taub's life-from a playful childhood to a young adulthood shattered within a labor camp in Schachendorf, Austria. Every day is a new battle to survive amid countless bodies. He makes a daring escape and finds compassion in people who offer food and clothing. Rebuilding the ashes of his family, Taub takes us on an extraordinary journey to Manhattan, where he and surviving family members eventually become successful entrepreneurs. He uses street smarts and intuition to make his fortune but still remains an enigmatic figure building a brick wall to contend with the great losses in his life. While giving historical accounts and sufficient background information of these different periods, the author often transcribes verbatim his grandfather's broken English to illustrate the man's unique style and humorous outlook on life. This incredibly witty and courageous story of perseverance will greatly appeal to the reader's emotions.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9780595349340\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"059534934X\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 208,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Biography & Autobiography\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"0.1.1.0.preview.3\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=hdk3KSGiLSsC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=hdk3KSGiLSsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=hdk3KSGiLSsC&pg=PA1&dq=magyar&hl=&cd=7&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.kw/books?id=hdk3KSGiLSsC&dq=magyar&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Magyar_Stars_Stripes.html?hl=&id=hdk3KSGiLSsC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.kw/books/download/Magyar_Stars_Stripes-sample-epub.acsm?id=hdk3KSGiLSsC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=hdk3KSGiLSsC&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"A Journey from Hungary Through the Holocaust and to New York Michael Lipiner\\u003cbr\\u003e\\n. PART I \\u003cb\\u003eMagyar\\u003c/b\\u003e 1848—1934 Our Town My name in Hungary is Taub, Sandor. I \\u003cbr\\u003e\\nPART I.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"aLpfAAAAcAAJ\",\n" +
            "   \"etag\": \"igj0C18QWm0\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/aLpfAAAAcAAJ\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Magyar Irodalom (Honi Nyelvünk rövid ismerete sannak a gondolatok helyes kifejezéséiei alkalmazása) Második kiaolás\",\n" +
            "    \"publishedDate\": \"1845\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"OTHER\",\n" +
            "      \"identifier\": \"BL:A0019335206\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"1.0.0.0.full.1\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=aLpfAAAAcAAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=aLpfAAAAcAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=aLpfAAAAcAAJ&pg=PA58&dq=magyar&hl=&cd=8&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=aLpfAAAAcAAJ&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-aLpfAAAAcAAJ\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"FREE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=aLpfAAAAcAAJ&rdid=book-aLpfAAAAcAAJ&rdot=1&source=gbs_api\"\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"ALL_PAGES\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": true,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=aLpfAAAAcAAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"FULL_PUBLIC_DOMAIN\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"А \\u003cb\\u003emagyar\\u003c/b\\u003e irodalomra nézve kiìlönösen nevezetes Er.wsy Jánosnak 1533. \\u003cbr\\u003e\\nÚjszigezen надои ser. \\u003cb\\u003emagyar\\u003c/b\\u003e пуеь. tana, Ethazinczy, mint nevezetes \\u003cb\\u003emagyar\\u003c/b\\u003e re&#39;\\u003cbr\\u003e\\ngîségei, Peslen ISGS-ban цуга kiadta. Erdösit Ferdinand, ama nyílt .eszä мешаю,\\u003cbr\\u003e\\n&nbsp;...\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"rgVjBK6Nv6oC\",\n" +
            "   \"etag\": \"WAOpHySWaGY\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/rgVjBK6Nv6oC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Developing and Evaluating Educational Programs for Students with Autism\",\n" +
            "    \"authors\": [\n" +
            "     \"Caroline I. Magyar\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Springer Science & Business Media\",\n" +
            "    \"publishedDate\": \"2010-11-12\",\n" +
            "    \"description\": \"Recent years have witnessed a marked increase both in the number of children diagnosed with autism spectrum disorders (ASDs) and those placed alongside their typically developing peers in general education classrooms. These events bring with them a plethora of challenges, particularly in the areas of program design and educational practices. Developing and Evaluating Educational Programs for Students with Autism offers systematic, evidence-based guidelines—as well as tools, checklists, and other resources—for creating effective learning environments for students across the autism spectrum and the grade span. Planning, development, implementation, and continuous evaluation are examined in detail in this practical volume, which features: An overview of the ASDs, with an emphasis on effective educational practice. In-depth discussion of the ASD Program Development and Evaluation Protocol. A staff training model for personnel working with students with ASD. A detailed framework for student support teams and family-school collaboration. Specific guidelines for conducting needs assessments and student evaluations. • Case examples of applications of the protocol on the program, school, and regional levels. Developing and Evaluating Educational Programs for Students with Autism is a uniquely rigorous and thorough reference benefiting school psychologists and special education professionals as well as those in allied educational and mental health fields, including clinical child, school, and developmental psychologists, psychiatrists, and other professionals working with children with autism.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1441963030\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781441963031\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 297,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Psychology\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"0.8.11.0.preview.3\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=rgVjBK6Nv6oC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=rgVjBK6Nv6oC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=rgVjBK6Nv6oC&printsec=frontcover&dq=magyar&hl=&cd=9&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=rgVjBK6Nv6oC&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-rgVjBK6Nv6oC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"FOR_SALE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"listPrice\": {\n" +
            "     \"amount\": 125.57,\n" +
            "     \"currencyCode\": \"USD\"\n" +
            "    },\n" +
            "    \"retailPrice\": {\n" +
            "     \"amount\": 125.57,\n" +
            "     \"currencyCode\": \"USD\"\n" +
            "    },\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=rgVjBK6Nv6oC&rdid=book-rgVjBK6Nv6oC&rdot=1&source=gbs_api\",\n" +
            "    \"offers\": [\n" +
            "     {\n" +
            "      \"finskyOfferType\": 1,\n" +
            "      \"listPrice\": {\n" +
            "       \"amountInMicros\": 1.2557E8,\n" +
            "       \"currencyCode\": \"USD\"\n" +
            "      },\n" +
            "      \"retailPrice\": {\n" +
            "       \"amountInMicros\": 1.2557E8,\n" +
            "       \"currencyCode\": \"USD\"\n" +
            "      }\n" +
            "     }\n" +
            "    ]\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED_FOR_ACCESSIBILITY\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.kw/books/download/Developing_and_Evaluating_Educational_Pr-sample-epub.acsm?id=rgVjBK6Nv6oC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.com.kw/books/download/Developing_and_Evaluating_Educational_Pr-sample-pdf.acsm?id=rgVjBK6Nv6oC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=rgVjBK6Nv6oC&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Planning, development, implementation, and continuous evaluation are examined in detail in this practical volume, which features: An overview of the ASDs, with an emphasis on effective educational practice.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"UUqQI5kSxhAC\",\n" +
            "   \"etag\": \"zWUh/Y4PNjQ\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/UUqQI5kSxhAC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Magyar Poetry\",\n" +
            "    \"subtitle\": \"Selections from Hungarian Poets\",\n" +
            "    \"authors\": [\n" +
            "     \"Sándor Petőfi\"\n" +
            "    ],\n" +
            "    \"publishedDate\": \"1899\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"OTHER\",\n" +
            "      \"identifier\": \"HARVARD:32044055062756\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": false,\n" +
            "     \"image\": false\n" +
            "    },\n" +
            "    \"pageCount\": 349,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"English poetry\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": false,\n" +
            "    \"contentVersion\": \"0.1.1.0.preview.0\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=UUqQI5kSxhAC&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=UUqQI5kSxhAC&printsec=frontcover&img=1&zoom=1&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.com.kw/books?id=UUqQI5kSxhAC&dq=magyar&hl=&cd=10&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.com.kw/books?id=UUqQI5kSxhAC&dq=magyar&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Magyar_Poetry.html?hl=&id=UUqQI5kSxhAC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"KW\",\n" +
            "    \"viewability\": \"NO_PAGES\",\n" +
            "    \"embeddable\": false,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": false\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=UUqQI5kSxhAC&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"NONE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   }\n" +
            "  }\n" +
            " ]\n" +
            "}";
    private static final String LOG_TAG = QueryUtils.class.getName();
    //JSON_RESPONSE static to used through the class
    private static String JSON_RESPONSE = "";

    //dummy creator
    private QueryUtils() {
    }

    //casting the url to URL format
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    //returns the JSon response specified by USGS_URL
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //helper method: builds JSon string from inputstream
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        JSON_RESPONSE = output.toString();
        return JSON_RESPONSE;
    }

    //returns the list of earthquakes defined by the USGS_URL
    public static List<Volume> extractVolumes() {
        Log.e("extractvolume", "started");
        //prepare url
        URL url = createUrl(GBAPI_URL);
       /*

        // get json data from USGS with makeHttpRequest
        try {
            JSON_RESPONSE = makeHttpRequest(url);
        } catch (IOException io) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", io);
        }

*/
        // Create an empty ArrayList that we can start adding earthquakes to
        List<Volume> volumes = new ArrayList<>();

        // Try to parse the JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {


            JSONObject volumeList = new JSONObject(JSONDUMMY);
            JSONArray items = volumeList.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                Log.e("JSONitem ", "no " + i);
                JSONObject currentVolume = items.getJSONObject(i);
                JSONObject volumeInfo = currentVolume.getJSONObject("volumeInfo");

                String title = volumeInfo.getString("title");
                Log.e("title", "is: " + title);

                try {
                    JSONArray authors = volumeInfo.getJSONArray("authors");
                    String author = authors.getString(0);
                    Log.e("author", "is: " + author);
                    volumes.add(new Volume(title, author));


                } catch (JSONException e) {
                    volumes.add(new Volume(title, "N/A"));
                    Log.e("no author", "N/A");
                }


            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return volumes;
    }

}