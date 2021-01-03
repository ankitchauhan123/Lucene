package com.ankit.lucene;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;

import java.io.IOException;

/***
 * @author: ankit chauhan
 * @date :  04-Dec-20
 ***/
public class Main {

    public static void main(String args[]) throws IOException, ParseException {
        String indexFolder="C:\\Users\\Imart\\Documents\\Personal\\Codes\\Lucene\\src\\main\\resources\\data\\index\\";
        Indexer indexer= new Indexer(indexFolder,"C:\\Users\\Imart\\Documents\\Personal\\Codes\\Lucene\\src\\main\\resources\\data\\raw");
        indexer.index();
        indexer.close();
        Searcher searcher= new Searcher(indexFolder);
        searcher.search("contents","Rudolf");

    }



}
