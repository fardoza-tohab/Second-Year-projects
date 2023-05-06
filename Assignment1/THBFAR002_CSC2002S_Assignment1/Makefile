# Makefile for Assignment1
# Fardoza Tohab
# 9 August 2021

JAVAC=/usr/bin/javac
.SUFFIXES:	.java	.class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC)	-d	$(BINDIR)/	-cp	$(BINDIR)	$<

CLASSES=MedianFilter.class	MFArray.class	ParallelMF.class	Performance.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default:	$(CLASS_FILES)

clean:
	rm	$(BINDIR)/*.class

runfilter:	$(CLASS_FILES)
	@java	-cp	bin	MedianFilter	"sampleInput1000.txt"	"3"	>	"data/outputSerial.txt"
	
runparallel:	$(CLASS_FILES)
	@java	-cp	bin	ParallelMF	"sampleInput1000.txt"	"3"	>	"data/outputParallel.txt"
	
runperformance:	$(CLASS_FILES)
	@java	-cp	bin	Performance	"sampleInput1000.txt"	"3"	>	"data/performance.txt"


