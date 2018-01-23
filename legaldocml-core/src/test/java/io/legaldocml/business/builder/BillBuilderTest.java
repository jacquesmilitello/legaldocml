package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Article;
import io.legaldocml.akn.element.Bill;
import io.legaldocml.akn.element.Content;
import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.I;
import io.legaldocml.akn.element.List;
import io.legaldocml.akn.element.Mod;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.element.Paragraph;
import io.legaldocml.akn.element.Point;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.element.BlocksBuilder;
import io.legaldocml.business.builder.element.HierarchyBuilder;
import io.legaldocml.business.builder.element.InlineReqTypeBuilder;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.business.builder.element.ModTypeBuilder;
import io.legaldocml.module.akn.v3.DefaultXmlWriterFactoryV3;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.Channels;

public class BillBuilderTest {

    private BusinessProvider provider = BusinessProvider.businessProvider("default");

    private static final AgentRef SOURCE = AgentRef.valueOf("redattore");

    @Test
    public void testBody() throws IOException {
        BillBusinessBuilder portionBuilder = new BillBusinessBuilder(provider, new Bill(), DefaultHierachyStrategy.COMPLETE) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }
        };

        portionBuilder.getBodyBuilder().parent().setTitle("DISEGNO DI LEGGE");

        HierarchyBuilder<Article> article = portionBuilder.getBodyBuilder().article();
        article.num().text("Art. 1.");
        InlineReqTypeBuilder<Heading> heading = article.heading( t-> t.setEid(NoWhiteSpace.valueOf("w1ab1ab3b1b1")));
        heading.i().text("(Modifiche al sistema elettorale della").<InlineTypeBuilder<I>>eol().text("Camera dei deputati)");

        HierarchyBuilder<Paragraph> paragraph = article.paragraph(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1")));
        paragraph.num().text("1. ");
        HierarchyBuilder<List> list = paragraph.list(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1")));
        list.intro().p().text("Al testo unico delle leggi recanti norme per la elezione della Camera dei deputati, di cui al decreto del Presidente della Repubblica 30 marzo 1957, n. 361, sono apportate le seguenti modificazioni:");

        // point A
        HierarchyBuilder<Point> point = list.point(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_a")));
        point.num().i().text("a)");

        InlineTypeBuilder<P> p = point.content(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_a"))).p();
        p.text("l'articolo 1 è sostituito dal seguente:");

        ModTypeBuilder<Mod> mod = p.mod(t-> t.setEid(NoWhiteSpace.valueOf("mod_1")));
        p = mod.quotedStructure().article(t-> t.setEid(NoWhiteSpace.valueOf("mod_1__art_1")))
            .content().p();
        p.text("«Art. 1. -- ").i().text("1.");
        p.text("La Camera dei deputati è eletta a suffragio universale, con voto diretto ed uguale, libero e segreto, espresso in favore di candidati in collegi uninominali, collegati a liste concorrenti nelle circoscrizioni.");
        p.i().text("2.");
        p.text(" Il territorio nazionale è diviso nelle circoscrizioni elettorali indicate nella tabella A allegata al presente testo unico. In queste circoscrizioni i seggi sono attribuiti, con metodo maggioritario ed eventuale secondo turno di votazione, a candidati concorrenti nei collegi uninominali e, in ragione proporzionale, a candidati concorrenti in liste circoscrizionali e nel collegio unico nazionale»;");

        // point B
        point = list.point(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_b")));
        point.num().i().text("b)");

        p = point.content(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_b__content"))).p();
        p.text("l'articolo 3 è sostituito dal seguente:");

        mod = p.mod(t-> t.setEid(NoWhiteSpace.valueOf("mod_2")));
        p = mod.quotedStructure().article(t-> t.setEid(NoWhiteSpace.valueOf("mod_2__art_3")))
                .content().p();
        p.text("«Art. 3. -- ").i().text("1.");
        p.text("L'assegnazione del numero dei seggi alle singole circoscrizioni, di cui alla tabella A allegata al presente testo unico, è effettuata, sulla base dei risultati dell'ultimo censimento generale della popolazione, riportati dalla più recente pubblicazione ufficiale dell'Istituto nazionale di statistica, con decreto del Presidente della Repubblica, su proposta del Ministro dell'interno, da emanare contemporaneamente al decreto di convocazione dei comizi.");
        p.i().text("2.").text(" Con il medesimo decreto di cui al comma 1 è determinato per ciascuna circoscrizione il numero di seggi che sono attribuiti nei collegi uninominali ed il numero di seggi da assegnare a candidati concorrenti in liste circoscrizionali.");
        p.i().text("3.").text(" Salvo quanto disposto dall'articolo 2, nell'ambito di ciascuna circoscrizione sono istituiti collegi uninominali in numero pari al 70 per cento, con arrotondamento all'unità superiore, del complesso dei seggi assegnati alla circoscrizione. I restanti seggi sono assegnati nella circoscrizione a candidati concorrenti nelle liste circoscrizionali, fatta salva una quota di 12 seggi che è assegnata in un collegio unico nazionale.");
        p.i().text("4.").text(" Alla assegnazione dei seggi nelle circoscrizioni concorrono soltanto le liste che ottengono una cifra elettorale circoscrizionale almeno pari al 5 per cento del totale dei voti validi espressi a livello circoscrizionale, calcolata secondo le modalità stabilite dall'articolo 77, comma 1, numeri 2) e 4)»;");

        // point C
        point = list.point(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_c")));
        point.num().i().text("c)");

        BlocksBuilder<Content> content = point.content(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_c__content")));
        p = content.p();
        p.text("il comma 2 dell'articolo 4 è sostituito dal seguente:");
        p = content.p();
        p.text("").i().text("2.");
        p.text(" Ogni elettore dispone di un voto per l'elezione del candidato nel collegio uninominale, che vale anche per la lista circoscrizionale cui è collegato il candidato uninominale prescelto»;");

        // point D
        addArticle_1_Point_D(list);

        // point E.
        addArticle_1_Point_E(list);

        /*

                        <an:point eId="">
                            <an:num>
                                <an:i></an:i>
                            </an:num>
                            <an:list eId="">




                        <an:point eId="art_1__para_1__list_1__point_f">
                            <an:num>
                                <an:i>f)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_f__content">
                                <an:p>dopo l'articolo 17 è inserito il seguente:</an:p>
                <an:p>«Art. 17<an:i>-bis. -- 1.</an:i> La presentazione delle candidature nei collegi uninominali è fatta per singoli candidati i quali si collegano a liste di cui all'articolo 1, comma 1, alle quali gli stessi aderiscono con l'accettazione della candidatura. La dichiarazione di collegamento deve essere accompagnata dall'accettazione scritta del rappresentante, di cui all'articolo 17, incaricato di effettuare il deposito della lista a cui il candidato nel collegio uninominale si collega. Nessun candidato può dichiarare il collegamento con più di una lista circoscrizionale, né accettare la candidatura in più di un collegio, anche se di circoscrizioni diverse. La candidatura della stessa persona in più di un collegio è nulla. È altresì nulla la candidatura nei collegi uninominali qualora nella medesima circoscrizione non sia presentata, ovvero sia ricusata, la lista circoscrizionale alla quale il candidato ha dichiarato di collegarsi.</an:p>
                                <an:p>
                                    <an:i>2. </an:i>Per ogni candidato nei collegi uninominali devono essere indicati il nome, il cognome, il luogo e la data di nascita, il collegio uninominale per il quale viene presentato e, salvo quanto stabilito al comma 1, il contrassegno della lista circoscrizionale cui egli è collegato. Il contrassegno, tra quelli depositati presso il Ministero dell'interno, è il medesimo che contraddistingue nella circoscrizione la lista alla quale il candidato è collegato. Per le candidate donne può essere indicato il solo cognome o può essere aggiunto il cognome del marito.</an:p>
                <an:p>
                                    <an:i>3. </an:i>La dichiarazione di presentazione dei candidati nei collegi uninominali deve contenere l'indicazione dei nominativi di due delegati effettivi e di due supplenti.</an:p>
                <an:p>
                                    <an:i>4.</an:i> La dichiarazione di presentazione dei singoli candidati nei collegi uninominali deve essere sottoscritta da non meno di 750 e da non più di 1.500 elettori iscritti nelle liste elettorali di comuni compresi nel collegio o, in caso di collegi ricompresi in un unico comune, iscritti alle sezioni elettorali di tali collegi. In caso di scioglimento della Camera dei deputati che ne anticipi la scadenza di oltre centoventi giorni, il numero delle sottoscrizioni è ridotto alla metà. Le sottoscrizioni devono essere autenticate da uno dei soggetti di cui all'articolo 14 della legge 21 marzo 1990, n. 53, e successive modificazioni. Si applicano anche alle candidature nei collegi uninominali le disposizioni di cui al comma 2 dell'articolo 18-<an:i>bis.</an:i>
                                </an:p>
                                <an:p>
                                    <an:i>5.</an:i> La candidatura deve essere accettata con dichiarazione firmata ed autenticata da un sindaco, da un notaio o da uno dei soggetti di cui all'articolo 14 della citata legge n. 53 del 1990. Per i cittadini residenti all'estero l'autenticazione della firma deve essere richiesta ad un ufficio diplomatico o consolare.</an:p>
                <an:p>
                                    <an:i>6.</an:i> L'accettazione della candidatura deve essere accompagnata da apposita dichiarazione dalla quale risulti che il candidato non ha accettato candidature in altri collegi»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_g">
                            <an:num>
                                <an:i>g)</an:i>
                            </an:num>
                            <an:list eId="art_1__para_1__list_1__point_g__list_1">
                                <an:intro eId="art_1__para_1__list_1__point_g__list_1__intro">
                                    <an:p>all'articolo 18-<an:i>bis</an:i>:</an:p>
                </an:intro>
                                <an:point eId="art_1__para_1__list_1__point_g__list_1__point_1">
                                    <an:num>1)</an:num>
                                    <an:content eId="art_1__para_1__list_1__point_g__list_1__point_1__content">
                                        <an:p> al comma 2, le parole: «abbiano effettuato le dichiarazioni di collegamento ai sensi dell'articolo 14-<an:i>bis</an:i>, comma 1, con almeno due partiti o gruppi politici di cui al primo periodo e» sono soppresse;</an:p>
                </an:content>
                                </an:point>
                                <an:point eId="art_1__para_1__list_1__point_g__list_1__point_2">
                                    <an:num>2)</an:num>
                                    <an:content eId="art_1__para_1__list_1__point_g__list_1__point_2__content">
                                        <an:p> il comma 3 è sostituito dai seguenti:</an:p>
                                        <an:p>«<an:i>3.</an:i> Ogni lista circoscrizionale, all'atto della presentazione, è composta da un elenco di candidati che concorrono ai seggi da assegnare nella circoscrizione. I candidati nelle liste circoscrizionali sono elencati in ordine alternato di genere. Il numero dei candidati concorrenti nella circoscrizione non può essere superiore alla metà dei seggi da distribuire su scala circoscrizionale, con arrotondamento all'unità superiore. Pena la nullità della candidatura, nessuno può essere presente nell'elenco dei candidati nella medesima lista in più di una circoscrizione, né può candidarsi in più di un collegio uninominale. Il numero minimo e massimo delle candidature è ridotto ad un quinto, con arrotondamento all'unità inferiore, per i partiti o gruppi politici rappresentativi di minoranze linguistiche.</an:p>
                                        <an:p>
                                            <an:i>3-</an:i>bis. Ogni lista nazionale, all'atto della presentazione, è composta da un elenco di candidati che concorrono ai seggi da assegnare nel collegio unico nazionale. I candidati nelle liste nazionali sono elencati in ordine alternato di genere e in ciascuna lista nessuno dei due generi può essere rappresentato in misura superiore al cinquanta per cento, con arrotondamento alla unità superiore»;</an:p>
                </an:content>
                                </an:point>
                            </an:list>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_h">
                            <an:num>
                                <an:i>h)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_h__content">
                                <an:p>dopo l'articolo 18<an:i>-bis</an:i> è inserito il seguente:</an:p>
                <an:p>«Art. 18<an:i>-ter.</an:i> -- <an:i>1. </an:i>A pena di inammissibilità, nel totale delle candidature presentate nei collegi uninominali e dei candidati inseriti nell'elenco che compone la lista circoscrizionale cui esse sono collegate nessuno dei due generi può essere rappresentato in misura superiore al cinquanta per cento, con arrotondamento alla unità superiore»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_i">
                            <an:num>
                                <an:i>i)</an:i>
                            </an:num>
                            <an:list eId="art_1__para_1__list_1__point_i__list_1">
                                <an:intro eId="art_1__para_1__list_1__point_i__list_1__intro">
                                    <an:p>all'articolo 22:</an:p>
                </an:intro>
                                <an:point eId="art_1__para_1__list_1__point_i__list_1__point_1">
                                    <an:num>1)</an:num>
                                    <an:content eId="w1ab1ab3b1b2c10b2b1">
                                        <an:p> al primo comma, numero 3), le parole: «al comma 2 dell'articolo 18-<an:i>bis</an:i>» sono sostituite dalle seguenti: «al comma 3 dell'articolo 18-<an:i>bis</an:i>»;</an:p>
                                    </an:content>
                                </an:point>
                                <an:point eId="art_1__para_1__list_1__point_i__list_1__point_2">
                                    <an:num>2)</an:num>
                                    <an:content eId="w1ab1ab3b1b2c10b3b1">
                                        <an:p> al primo comma, dopo il numero 6) sono aggiunti i seguenti:</an:p>
                                        <an:p>«6<an:i>-bis)</an:i> dichiara nulle le candidature nei collegi uninominali di candidati già presentatisi in altro collegio;</an:p>
                                        <an:p>6<an:i>-ter)</an:i> dichiara nulle le candidature nei collegi uninominali di candidati che abbiano dichiarato il collegamento con più di una lista circoscrizionale e dichiara nulle le candidature uninominali di candidati che non risultino collegate ad una lista circoscrizionale validamente presentata»;</an:p>
                                    </an:content>
                                </an:point>
                            </an:list>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_l">
                            <an:num>
                                <an:i>l)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_l__content">
                                <an:p>l'articolo 24 è sostituito dal seguente:</an:p>
                <an:p>«Art. 24. --<an:i> 1. </an:i>L'ufficio centrale circoscrizionale, appena scaduto il termine stabilito per la presentazione dei ricorsi, o, nel caso in cui sia stato presentato ricorso, appena ricevuta la comunicazione della decisione dell'Ufficio centrale nazionale, compie le seguenti operazioni:</an:p>
                                <an:p>1) stabilisce, per ciascun collegio, mediante sorteggio da effettuare alla presenza dei delegati dei candidati nei collegi uninominali e delle liste, appositamente convocati, il numero d'ordine da assegnare a ciascun candidato nel rispettivo collegio. I candidati nei collegi uninominali sono riportati sulle schede e sul manifesto del relativo collegio secondo l'ordine risultato dal sorteggio; il contrassegno di ogni candidato è riportato sulle schede di votazione e sui manifesti, accanto al nominativo del candidato stesso;</an:p>
                                <an:p>2) comunica ai delegati delle liste dei candidati nei collegi uninominali le definitive determinazioni adottate;</an:p>
                                <an:p>3) trasmette immediatamente alla prefettura capoluogo della circoscrizione i nominativi dei candidati nei collegi uninominali e le liste ammessi, con i relativi contrassegni, i quali devono essere riprodotti sulle schede di votazione con i colori del contrassegno depositato presso il Ministero dell'interno ai sensi dell'articolo 14, per la stampa delle schede medesime e per l'adempimento di cui al numero 4);</an:p>
                <an:p>4) provvede, per mezzo della prefettura capoluogo della circoscrizione, alla stampa, su distinti manifesti riproducenti i rispettivi contrassegni, dei nominativi dei candidati nei singoli collegi uninominali e delle liste nonché alla trasmissione di essi ai sindaci dei comuni del collegio per la pubblicazione nell'albo pretorio ed in altri luoghi pubblici entro il quindicesimo giorno precedente la data delle elezioni. Tre copie di ciascun manifesto devono essere consegnate ai presidenti dei singoli uffici elettorali di sezione, una a disposizione dell'ufficio e le altre per l'affissione nella sala della votazione»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_m">
                            <an:num>
                                <an:i>m)</an:i>
                            </an:num>
                            <an:list eId="art_1__para_1__list_1__point_m__list_1">
                                <an:intro eId="art_1__para_1__list_1__point_m__list_1__intro">
                                    <an:p>all'articolo 25:</an:p>
                </an:intro>
                                <an:point eId="art_1__para_1__list_1__point_m__list_1__point_1">
                                    <an:num>1)</an:num>
                                    <an:content eId="art_1__para_1__list_1__point_m__list_1__point_1__content">
                                        <an:p> al primo comma, le parole: «di cui all'articolo 20» sono sostituite dalle seguenti: «di cui agli articoli 17-<an:i>bis</an:i> e 20» e le parole: «due rappresentanti della lista» sono sostituite dalle seguenti: «due rappresentanti del candidato nel collegio uninominale o della lista»;</an:p>
                </an:content>
                                </an:point>
                                <an:point eId="art_1__para_1__list_1__point_m__list_1__point_3">
                                    <an:num>3)</an:num>
                                    <an:content eId="art_1__para_1__list_1__point_m__list_1__point_3__content">
                                        <an:p> al terzo comma, nel primo periodo, le parole: «i delegati di lista» sono sostituite dalle seguenti: «i delegati dei candidati nei collegi uninominali e di lista» e le parole: «del deposito delle liste dei candidati» sono sostituite dalle seguenti: «del deposito delle candidature nei collegi uninominali e delle liste di candidati»; nel secondo periodo, le parole: «del deposito delle liste» sono sostituite dalle seguenti: «del deposito delle candidature nei collegi uninominali e delle liste»;</an:p>
                                    </an:content>
                                </an:point>
                            </an:list>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_n">
                            <an:num>
                                <an:i>n)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_n__content">
                                <an:p>all'articolo 26, primo comma, le parole: «Il rappresentante di ogni lista di candidati» sono sostituite dalle seguenti: «Il rappresentante di ogni candidato nel collegio uninominale e di ogni lista di candidati»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_o">
                            <an:num>
                                <an:i>o)</an:i>
                            </an:num>
                            <an:list eId="art_1__para_1__list_1__point_o__list_1">
                                <an:intro eId="art_1__para_1__list_1__point_o__list_1__intro">
                                    <an:p>all'articolo 30:</an:p>
                </an:intro>
                                <an:point eId="art_1__para_1__list_1__point_o__list_1__point_1">
                                    <an:num>1)</an:num>
                                    <an:content eId="art_1__para_1__list_1__point_o__list_1__point_1__content">
                                        <an:p> al numero 4), alle parole: «tre copie del manifesto» sono premesse le seguenti: «tre copie del manifesto contenente i nominativi dei candidati nei collegi uninominali e»;</an:p>
                                    </an:content>
                                </an:point>
                                <an:point eId="art_1__para_1__list_1__point_o__list_1__point_2">
                                    <an:num>2)</an:num>
                                    <an:content eId="art_1__para_1__list_1__point_o__list_1__point_2__content">
                                        <an:p> al numero 6), le parole: «di lista» sono sostituite dalle seguenti: «dei candidati nei collegi uninominali e di lista»;</an:p>
                                    </an:content>
                                </an:point>
                            </an:list>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_p">
                            <an:num>
                                <an:i>p)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_p__content">
                                <an:p>l'articolo 31 è sostituito dal seguente:</an:p>
                <an:p>«Art. 31. -- <an:i>1. </an:i>Le schede sono di carta consistente, sono fornite a cura del Ministero dell'interno con le caratteristiche essenziali del modello descritto nelle tabelle B e C allegate al presente testo unico e riproducono in facsimile i contrassegni di tutte le candidature nei collegi uninominali e di tutte le liste regolarmente presentate nella circoscrizione, secondo le disposizioni di cui all'articolo 24. Le schede riportano in successione, secondo l'ordine del sorteggio, il cognome ed il nome di ciascun candidato nel collegio uninominale con accanto il rispettivo contrassegno e, accanto a questo, il contrassegno della lista collegata al candidato ed i nomi dei candidati in essa riportati. I contrassegni devono essere riprodotti sulle schede con il diametro di centimetri tre. Qualora per l'assegnazione del seggio nei collegi uninominali debba procedersi ad un secondo turno di votazione, nella scheda, con le medesime caratteristiche del modello di cui alla tabella B, sono riportati soltanto i nomi ed i contrassegni dei candidati i quali, nel primo turno elettorale, abbiano ottenuto il voto di almeno il 10 per cento degli elettori iscritti nelle liste elettorali del collegio e che non abbiano presentato espressa rinuncia secondo i termini previsti dall'articolo 77, comma 1, numero 1)»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_q">
                            <an:num>
                                <an:i>q)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_q__content">
                                <an:p>all'articolo 42, settimo comma, le parole: «del manifesto contenente le liste dei candidati» sono sostituite dalle seguenti: «dei manifesti contenenti l'elenco delle candidature uninominali e le liste dei candidati circoscrizionali e nazionali»;</an:p>
                            </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_r">
                            <an:num>
                                <an:i>r)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_r__content">
                                <an:p>all'articolo 58, il secondo e il terzo comma sono sostituiti dai seguenti:</an:p>
                <an:p>«L'elettore si reca ad una delle apposite cabine e, senza che sia avvicinato da alcuno, vota tracciando, con la matita, sulla scheda un solo segno, comunque apposto, nel rettangolo contenente il cognome e nome del candidato prescelto ed il contrassegno relativo. Sono vietati altri segni o indicazioni. L'elettore piega poi la scheda secondo le linee in essa tracciate e la chiude inumidendone la parte gommata. Di queste operazioni il presidente fornisce preventive istruzioni, astenendosi da ogni esemplificazione.</an:p>
                                <an:p>Compiuta l'operazione di voto, l'elettore consegna al presidente la scheda chiusa e la matita. Il presidente constata la chiusura della scheda e, ove questa non sia chiusa, invita l'elettore a chiuderla, facendolo rientrare in cabina; ne verifica l'identità esaminando la firma e il bollo, e confrontando il numero scritto sull'appendice con quello scritto sulla lista; ne distacca l'appendice seguendo la linea tratteggiata e pone la scheda stessa nell'urna»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_s">
                            <an:num>
                                <an:i>s)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_s__content">
                                <an:p>all'articolo 67, primo comma, al numero 2), terzo periodo, dopo le parole: «nonché i rappresentanti» sono inserite le seguenti: «dei candidati nei collegi uninominali e»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_t__content">
                            <an:num>
                                <an:i>t)</an:i>
                            </an:num>
                            <an:list eId="art_1__para_1__list_1__point_t__list_1">
                                <an:intro eId="art_1__para_1__list_1__point_t__list_1__intro">
                                    <an:p>all'articolo 68:</an:p>
                </an:intro>
                                <an:point eId="art_1__para_1__list_1__point_t__list_1__point_1">
                                    <an:num>1)</an:num>
                                    <an:content eId="art_1__para_1__list_1__point_t__list_1__point_1__content">
                                        <an:p> i commi 1 e 2 riacquistano efficacia nel testo vigente prima della data di entrata in vigore della legge 21 dicembre 2005, n. 270;</an:p>
                                    </an:content>
                                </an:point>
                                <an:point eId="art_1__para_1__list_1__point_t__list_1__point_2">
                                    <an:num>2)</an:num>
                                    <an:content eId="art_1__para_1__list_1__point_t__list_1__point_2__content">
                                        <an:p> i commi 3 e 3-<an:i>bis</an:i> sono abrogati;</an:p>
                                    </an:content>
                                </an:point>
                            </an:list>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_u">
                            <an:num>
                                <an:i>u)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_u__content">
                                <an:p>all'articolo 72, secondo comma, alle parole: «di lista presenti» sono premesse le seguenti: «dei candidati nel collegio uninominale e»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_v">
                            <an:num>
                                <an:i>v)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_v__content">
                                <an:p>il terzo comma dell'articolo 73 è sostituito dal seguente:</an:p>
                <an:p>«Alla cassetta, all'urna ed al plico devono apporsi le indicazioni del collegio e della sezione, il sigillo col bollo dell'ufficio e quello dei rappresentanti dei candidati nel collegio uninominale e di lista che vogliano aggiungere il proprio, nonché le firme del presidente e di almeno due scrutatori»;</an:p>
                            </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_z">
                            <an:num>
                                <an:i>z)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_z__content">
                                <an:p>all'articolo 75, primo comma, secondo periodo, dopo le parole: «dai rappresentanti» sono inserite le seguenti: «dei candidati nel collegio uninominale e»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_aa">
                            <an:num>
                                <an:i>aa)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_aa__content">
                                <an:p>l'articolo 77 è sostituito dal seguente:</an:p>
                <an:p>«Art. 77. -- <an:i>1.</an:i> L'ufficio centrale circoscrizionale, compiute le operazioni di cui all'articolo 76, facendosi assistere, ove lo ritenga opportuno, da uno o più esperti scelti dal presidente:</an:p>
                                <an:p>1) determina il totale dei voti validi ottenuti da ciascun candidato nei collegi uninominali e, in conformità ai risultati accertati, proclama eletto in ciascun collegio uninominale il candidato che ha ottenuto un numero di voti validi almeno pari alla metà più uno, con arrotondamento all'unità superiore, del totale dei voti validi espressi nel collegio. Qualora nessun candidato abbia ottenuto tale risultato, si procede nella seconda domenica successiva ad un secondo turno di votazione al quale sono ammessi i candidati che nel primo turno elettorale abbiano ottenuto voti in numero pari ad almeno il 10 per cento degli elettori iscritti nelle liste elettorali del collegio e che non abbiano presentato espressa rinuncia all'ufficio centrale circoscrizionale entro il primo venerdì successivo allo svolgimento del primo turno elettorale. Nel secondo turno elettorale risulta eletto il candidato che ottiene il maggior numero di voti validi. In caso di parità l'ufficio elettorale circoscrizionale proclama eletto tra essi il candidato che al primo turno elettorale ha ottenuto il maggior numero di voti;</an:p>
                <an:p>2) determina la cifra elettorale circoscrizionale teorica di ogni lista. Tale cifra è data dalla somma dei voti conseguiti nel primo turno elettorale dai candidati dei collegi uninominali collegati alla lista; sono escluse dal riparto al livello circoscrizionale le liste che non abbiano ottenuto una cifra teorica pari almeno al 5 per cento del totale dei voti validamente espressi;</an:p>
                                <an:p>3) determina il quoziente elettorale circoscrizionale; a tal fine aumenta di una unità il numero dei seggi da assegnare nella circoscrizione ai sensi dell'articolo 3, e divide per tale numero il totale dei voti validi espressi per tutti i candidati nei collegi uninominali della circoscrizione nel primo turno elettorale; la parte intera di tale risultato costituisce il quoziente elettorale circoscrizionale;</an:p>
                <an:p>4) al termine delle operazioni del secondo turno elettorale, calcola la cifra elettorale circoscrizionale effettiva di ciascuna lista ammessa sottraendo alla cifra elettorale circoscrizionale teorica di ciascuna lista, determinata ai sensi del numero 2), i voti ottenuti nel primo turno elettorale dai candidati nei collegi uninominali ad essa collegati che siano risultati eletti;</an:p>
                                <an:p>5) divide la cifra elettorale circoscrizionale effettiva di ciascuna lista calcolata ai sensi del numero 4) per il quoziente elettorale circoscrizionale determinato ai sensi del numero 3) e attribuisce a ciascuna lista un numero di seggi pari al numero di quozienti interi ottenuti da tale operazione; i seggi che residuano sono attribuiti, uno ciascuno, alle liste i cui voti residuali risultino più alti, con arrotondamento all'unità superiore; a tal fine si considerano voti residuali anche le cifre elettorali delle liste ammesse che non hanno dato luogo alla attribuzione di alcun quoziente intero;</an:p>
                <an:p>6) determina il numero dei voti validi non utilizzati da ciascuna lista per l'attribuzione dei seggi in sede circoscrizionale;</an:p>
                <an:p>7) al termine delle operazioni relative al secondo turno di votazione comunica all'Ufficio centrale nazionale, a mezzo di estratto del verbale, il totale dei voti validi ottenuti da ciascuna lista, non utilizzati per l'attribuzione di seggi alle liste circoscrizionali.</an:p>
                                <an:p>
                                    <an:i>2.</an:i> Al termine delle operazioni di cui al comma 1, il presidente dell'ufficio centrale circoscrizionale proclama eletti i candidati nei collegi uninominali secondo le risultanze delle attribuzioni di cui al comma 1, numero 1), e successivamente proclama eletti i candidati compresi nell'elenco di ciascuna lista circoscrizionale alla quale l'ufficio ha attribuito seggi ai sensi del comma 1, numero 5), procedendo secondo l'ordine in cui i candidati sono collocati nella lista e fino a concorrenza del numero dei seggi ai quali ciascuna lista ha diritto. Qualora il numero di seggi da assegnare risulti superiore al numero dei candidati contenuti nella lista circoscrizionale, i seggi ulteriori vengono assegnati ai candidati nei collegi uninominali collegati alla medesima lista che abbiano ottenuto la maggiore cifra elettorale. Parimenti, il presidente dell'ufficio centrale circoscrizionale procede alla proclamazione dei candidati eletti nei collegi uninominali secondo l'attribuzione dei seggi effettuata a seguito del secondo turno di votazione»;</an:p>
                            </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_bb">
                            <an:num>
                                <an:i>bb)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_bb__content">
                                <an:p>all'articolo 79, ai commi quinto e sesto, alle parole: «delle liste dei candidati» sono premesse le seguenti: «dei candidati nei collegi uninominali e»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_cc">
                            <an:num>
                                <an:i>cc)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_cc__content">
                                <an:p>l'articolo 83 è sostituito dal seguente:</an:p>
                <an:p>«Art. 83. -- <an:i>1.</an:i> Al termine delle operazioni degli uffici centrali circoscrizionali relative al secondo turno di votazione, l'Ufficio centrale nazionale, ricevuti gli estratti dei verbali da tutti gli uffici centrali circoscrizionali, facendosi assistere, ove lo ritenga opportuno, da uno o più esperti scelti dal presidente:</an:p>
                <an:p>1) determina:</an:p>
                                <an:p>
                                    <an:i>a)</an:i> il totale nazionale dei voti attribuiti ai candidati nei collegi uninominali che non siano stati eletti e le cui liste circoscrizionali collegate non abbiano conseguito seggi nel riparto proporzionale circoscrizionale;</an:p>
                                <an:p>
                                    <an:i>b)</an:i> le liste che abbiano ottenuto una quota dei voti di cui alla lettera <an:i>a)</an:i> che si siano presentate con il medesimo contrassegno in almeno cinque circoscrizioni;</an:p>
                                <an:p>
                                    <an:i>c)</an:i> il totale nazionale dei voti di cui alla lettera <an:i>a)</an:i> spettante a ciascuna lista di cui alla lettera <an:i>b)</an:i>;</an:p>
                                <an:p>2) divide la cifra elettorale di ciascuna lista ammessa alla attribuzione dei seggi successivamente per 1, 2, 3, 4, ... sino a concorrenza del numero dei seggi da attribuire e quindi sceglie, tra i quozienti così ottenuti, i più alti, in numero eguale a quello dei seggi da attribuire, disponendoli in graduatoria decrescente. Ciascuna lista ottiene tanti seggi quanti sono i quozienti compresi nella graduatoria. A parità di quoziente, nelle cifre intere e decimali, il seggio è attribuito alla lista che ha ottenuto la maggiore cifra elettorale e, a parità di quest'ultima, per sorteggio»;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_dd">
                            <an:num>
                                <an:i>dd)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_dd__content">
                                <an:p>l'articolo 84 è sostituito dal seguente:</an:p>
                <an:p>«Art. 84. -- <an:i>1.</an:i> Compiute le operazioni di cui all'articolo 83 l'Ufficio centrale nazionale procede ad assegnare i seggi attribuiti a ciascuna lista a seguito delle operazioni di cui al comma 1, numero 2), del medesimo articolo, secondo l'ordine di presentazione dei candidati.</an:p>
                <an:p>
                                    <an:i>2.</an:i> Di tutte le operazioni dell'Ufficio centrale nazionale viene redatto, in duplice esemplare, apposito verbale; un esemplare è rimesso alla Segreteria generale della Camera dei deputati la quale ne rilascia ricevuta; l'altro esemplare è depositato presso la cancelleria della Corte di cassazione»;</an:p>
                            </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_ee">
                            <an:num>
                                <an:i>ee)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_ee__content">
                                <an:p>l'articolo 85 è abrogato;</an:p>
                </an:content>
                        </an:point>
                        <an:point eId="art_1__para_1__list_1__point_ff">
                            <an:num>
                                <an:i>ff)</an:i>
                            </an:num>
                            <an:content eId="art_1__para_1__list_1__point_ff__content">
                                <an:p>all'articolo 86, i commi 1 e 2 sono sostituiti dai seguenti:</an:p>
                <an:p>«<an:i>1.</an:i> Il seggio che rimanga vacante per qualsiasi causa anche sopravvenuta è attribuito nella medesima circoscrizione al candidato che segue nella graduatoria compilata ai sensi dell'articolo 77. Qualora cessi dalla carica il deputato proclamato nei collegi uninominali si dà luogo ad elezione suppletiva.</an:p>
                <an:p>
                                    <an:i>2. </an:i>Si applicano, quando ne ricorrono le circostanze, le disposizioni di cui all'articolo 83».</an:p>
                </an:content>
                        </an:point>
                    </an:list>
                </an:paragraph>
            </an:article>
                */
        new DefaultXmlWriterFactoryV3().writePermissive(Channels.newChannel(System.out), portionBuilder.getAkomaNtoso());

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(baos), portionBuilder.getAkomaNtoso());
//
//        Document expected = ReaderHelper.load("/xml/v3/it_senato_ddl_2013.xml");
//        Document actual = ReaderHelper.load(new ByteArrayInputStream(baos.toByteArray()));
//
//        XmlUnitHelper.compare(
//                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "intro").item(0),
//                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "intro").item(0)
//        );

    }


    public void addArticle_1_Point_D(HierarchyBuilder<List> list) {
        // point D
        HierarchyBuilder<Point> point = list.point(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_d")));
        point.num().i().text("d)");

        list = point.list(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_d__list_1")));
        list.intro(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_d__list_1_intro"))).p().text("all'articolo 14:");

        // point D.1
        point = list.point(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_d__list_1__point_1")));
        point.num().i().text("1)");
        InlineTypeBuilder<P> p = point.content(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_d__list_1__point_1__content")))
                .p();
        p.text(" al primo comma, primo periodo, sono aggiunte in fine le seguenti parole: «e le candidature nei collegi uninominali che ad esse si collegano, secondo quanto disposto dall'articolo 17-");
        p.i().text("bis");
        p.text("»;");

        // point D.2
        point = list.point(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_d__list_1__point_2")));
        point.num().i().text("2)");

        BlocksBuilder<Content> content = point.content();
        content.p().text(" dopo il quarto comma è inserito il seguente:");
        content.p().text("«Non è ammessa la presentazione di contrassegni che riproducono in qualsiasi loro parte e composizione il nome, il cognome o il nome di notorietà di uno o più candidati presenti nelle liste o di uno o più candidati nei collegi uninominali collegati al partito o gruppo politico organizzato che presenta il contrassegno»;");

    }

    public void addArticle_1_Point_E(HierarchyBuilder<List> list) {
        HierarchyBuilder<Point> point = list.point(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_e")));
        point.num().i().text("e)");
        InlineTypeBuilder<P> p = point.content(t-> t.setEid(NoWhiteSpace.valueOf("art_1__para_1__list_1__point_e__content")))
                .p();
        p.text("l'articolo 14-");
        p.i().text("bis");
        p.text(" è abrogato;");
    }
}
