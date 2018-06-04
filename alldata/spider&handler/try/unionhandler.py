import csv

musical = csv.reader(open('try.csv'))

lw = csv.reader(open("lyricswriter.csv"))

#csvfile = open("musical_mw.csv", "w")
csvfile = open("musical_lw.csv", "w")
writer = csv.writer(csvfile)



for m in musical:
    mw = csv.reader(open('lyricswriter.csv'))
    for w in mw:
        if w[1] in m[5]:
            id = m[0]
            name = w[0]
            writer.writerow([id, name])

print("f")
csvfile.close()
