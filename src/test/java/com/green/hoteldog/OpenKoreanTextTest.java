package com.green.hoteldog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import scala.collection.Seq;

import java.util.List;


@SpringBootTest
@ActiveProfiles("local-live")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // DB 사용 설정
@RunWith(SpringRunner.class)
public class OpenKoreanTextTest {

    private static final Logger log = LoggerFactory.getLogger(OpenKoreanTextTest.class);

    @Test
    public void Test() {

        String text = "엄준식은살아있다호애애애애!!#순대초밥 ";

        // Normalize
        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
        System.out.println("normalized" + normalized);

        // Tokenize
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
        System.out.println(OpenKoreanTextProcessorJava.tokensToJavaStringList(tokens));
        List<String> list = OpenKoreanTextProcessorJava.tokensToJavaStringList(tokens);
        System.out.println("list : " + list);
        System.out.println(OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens));

        // Phrase extraction
        List<KoreanPhraseExtractor.KoreanPhrase> phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, true, true);
        System.out.println(phrases);

        phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, false, true);
        System.out.println(phrases);

        phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, true, false);
        System.out.println(phrases);
    }
}
