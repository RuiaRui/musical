import urllib
import urllib.request
import csv
import codecs
from bs4 import BeautifulSoup


def get(url, coding):
    header = {'User-Agent': 'Mozilla/5.0'}
    req = urllib.request.Request(url, headers=header)
    response = urllib.request.urlopen(req)
    html = response.read()
    htm = html.decode(coding, 'ignore')
    return htm


def getFirsttable(content):
    soup = BeautifulSoup(content, 'html.parser')  # 声明BeautifulSoup对象
    t = soup.find("table")
    return t


def getAllTables(content):
    soup = BeautifulSoup(content, 'html.parser')  # 声明BeautifulSoup对象
    ts = soup.find_all("table")
    return ts


### 得到表头
### 后来发现csv不需要表头，mdzz

def getTableHead(t):
    # 3soup1 = BeautifulSoup(t, 'html.parser')
    tableheads = t.find_all(name='th')
    thc = list()
    for tablehead in tableheads:
        thc.append(tablehead.get_text())
    thc.remove(thc[0])
    return thc


def writeInCsv(list):
    id = list[0]
    title = list[1]
    genre = list[2]
    open = list[3]
    close = list[4]
    prof = list[5]
    comment = list[6]
    type=2

    csv_writer.writerow([id, title, genre, open, close, prof, comment,type])


def getTableContent(t):
    trs = t.find_all(name="tr")
    # print(trs)
    for tr in trs:
        tds = tr.find_all(name="td")
        row = list()
        url = ""
        # print(tds)
        if len(tds) == 7:
            for tb in tds:
                row.append(tb.get_text())
            print(row)
            writeInCsv(row)
        row.clear()


t_url = "http://en.wikipedia.org/wiki/List_of_Broadway_musicals_stars"

html = get(t_url, "utf-8")
f = open(r'muscialstar.csv', mode='a', encoding='utf8')
writer = csv.writer(f)
csv_writer = csv.writer(f)
i = 1



# soup = BeautifulSoup(html, 'html.parser')
# divs=soup.find_all(name="div",attrs={"class":"div-col columns column-width"})
# for div in divs:
#     content = div.find_all("li")
#
#     href = ""
#     for li in content:
#         id = i
#
#         name = li.get_text()
#         if li.find("a") is not None:
#             href = li.find("a").get('href')
#             url = "http://en.wikipedia.org" + href
#         else:
#             url = ""
#         csv_writer.writerow([id, name, url])
#         i = i + 1
#

print("finish")
f.close()
