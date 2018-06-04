import csv
import re

csv_reader = csv.reader(open('try.csv'))
musicw = list()
lyricsw = list()
for row in csv_reader:
    if "Various artists" in row[4]:
        continue
    # else:
    #     if "," in row[5]:
    #         names = row[5].split(",")
    #     else:
    #         names=row[5]
    if row[4] in musicw:
            continue
    musicw.append(row[4])

# for row in csv_reader:
#     if "Various artists" in row[5]:
#         continue
#
#     if row[5] in lyricsw:
#         continue
#     lyricsw.append(row[5])

print(len(musicw))
print(len(lyricsw))
#csvfile = open("lyricswriter.csv", 'w')
csvfile = open("musicalwriter.csv", "w")
writer = csv.writer(csvfile)
i = 1
for m in musicw:
    writer.writerow([i, m])
    i = i + 1

# writer = csv.writer(csvfile)
# j=1
# for l in lyricsw:
#     writer.writerow([j,l])
#     j=j+1


csvfile.close()
#csvfile2.close()
