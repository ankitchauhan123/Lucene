package com.ankit.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;

/***
 * @author: ankit chauhan
 * @date :  03-Jan-21
 ***/
public class Searcher {
    private String indexDir;
    private IndexSearcher indexSearcher;


    public Searcher(String indexDir) throws IOException {
        this.indexDir=indexDir;
        IndexReader reader= DirectoryReader.open(FSDirectory.open(FileSystems.getDefault().getPath(indexDir)));
        indexSearcher= new IndexSearcher(reader);
    }

    public void search(String inField, String query) throws ParseException, IOException {
        Analyzer analyzer= new StandardAnalyzer();
        Query q= new QueryParser(inField,analyzer).parse(query);
        TopDocs hits= indexSearcher.search(q,10);
        System.out.println("Total Documents : "+hits.totalHits);
        for (ScoreDoc scoreDoc:hits.scoreDocs){
            Document doc=indexSearcher.doc(scoreDoc.doc);
            System.out.println(doc.get("fullpath"));

        }
    }


}
