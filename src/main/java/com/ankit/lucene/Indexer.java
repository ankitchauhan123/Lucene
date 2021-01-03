package com.ankit.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/***
 * @author: ankit chauhan
 * @date :  04-Dec-20
 ***/
public class Indexer {
    private IndexWriter indexWriter;
    private String sourceFolder;
    private String indexFolder;


    public Indexer(String indexFolder, String sourceFolder) throws IOException {
        this.indexFolder = indexFolder;
        this.sourceFolder = sourceFolder;
        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(indexFolder));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
        indexWriter = new IndexWriter(directory, indexWriterConfig);
    }

    public void close() throws IOException {
        if (indexWriter != null) {
            indexWriter.close();
        }
    }

    public void index(String inputDir){

    }

    public Document getDocument(File f){
        Document doc= new Document();
        doc.add(new Field(""));
    }


}
