class Musical(object):

    def __init__(self):
        self.id = None
        self.production = None
        self.year = None
        self.venue = None
        self.musicW = None
        self.lyricsW = None
        self.bookW = None
        self.notes = None

    def getId(self):
        return self.id

    def setId(self, value):
        self.id = value

    def getProduction(self):
        return self.production

    def setProduction(self, value):
        self.production = value

    def getYear(self):
        return self.year

    def setYear(self, value):
        self.year = value

    def setVenuse(self, value):
        self.venue = value

    def getVenue(self):
        return self.venue

    def setMusicW(self, value):
        self.musicW = value

    def getMusicW(self):
        return self.musicW

    def setLyricsW(self, value):
        self.lyricsW = value

    def getLyricsW(self):
        return self.lyricsW

    def setBookW(self, value):
        self.bookW = value

    def getBookW(self):
        return self.bookW

    def setNotes(self, value):
        self.notes = value

    def getNotes(self):
        return self.notes
