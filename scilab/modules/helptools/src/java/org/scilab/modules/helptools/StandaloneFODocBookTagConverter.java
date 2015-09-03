package org.scilab.modules.helptools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.scilab.modules.helptools.image.Image;
import org.scilab.modules.helptools.image.ImageConverter;
import org.xml.sax.SAXException;

public class StandaloneFODocBookTagConverter extends DocbookTagConverter {
	public static final String FO_BASIC_LINK = "fo:basic-link";
	public static final String FO_BLOCK = "fo:block";
	public static final String FO_EXTERNAL_GRAPHIC = "fo:external-graphic";
	public static final String FO_INLINE = "fo:inline";
	
	public static final Map<String, String> CHAPTER_TITLE_ATTRS = new HashMap<>();
	public static final Map<String, String> PART_TITLE_ATTRS = new HashMap<>();
	public static final Map<String, String> REFSECTION_TITLE_ATTRS = new HashMap<>();
	public static final Map<String, String> REFSYNOPSISDIV_TITLE_ATTRS = new HashMap<>();
	public static final Map<String, String> SECTION_TITLE_ATTRS = new HashMap<>();
	
	public static final Map<String, String> CAUTION_ATTRS = new HashMap<>();
	public static final Map<String, String> CAPTION_ATTRS = new HashMap<>();
	public static final Map<String, String> CODE_ATTRS = new HashMap<>();
	public static final Map<String, String> COMMAND_ATTRS = new HashMap<>();
	public static final Map<String, String> CONSTANT_ATTRS = new HashMap<>();
	public static final Map<String, String> EMPHASIS_ATTRS = new HashMap<>();
	public static final Map<String, String> FIRSTNAME_ATTRS = new HashMap<>();
	public static final Map<String, String> FUNCTION_ATTRS = new HashMap<>();
	public static final Map<String, String> IMPORTANT_ATTRS = new HashMap<>();
	public static final Map<String, String> INFORMALEQUATION_ATTRS = new HashMap<>();
	public static final Map<String, String> LITERAL_ATTRS = new HashMap<>();
	public static final Map<String, String> NOTE_ATTRS = new HashMap<>();
	public static final Map<String, String> OPTION_ATTRS = new HashMap<>();
	public static final Map<String, String> PARA_ATTRS = new HashMap<>();
	public static final Map<String, String> PROGRAMLISTING_ATTRS = new HashMap<>();
	public static final Map<String, String> PUBDATE_ATTRS = new HashMap<>();
	public static final Map<String, String> REFNAME_ATTRS = new HashMap<>();
	public static final Map<String, String> REFPURPOSE_ATTRS = new HashMap<>();
	public static final Map<String, String> REPLACEABLE_ATTRS = new HashMap<>();
	public static final Map<String, String> REVNUMBER_ATTRS = new HashMap<>();
	public static final Map<String, String> SCREEN_ATTRS = new HashMap<>();
	public static final Map<String, String> SUBSCRIPT_ATTRS = new HashMap<>();
	public static final Map<String, String> SUPERSCRIPT_ATTRS = new HashMap<>();
	public static final Map<String, String> SURNAME_ATTRS = new HashMap<>();
	public static final Map<String, String> SYNOPSIS_ATTRS = new HashMap<>();
	public static final Map<String, String> TERM_ATTRS = new HashMap<>();
	public static final Map<String, String> TIP_ATTRS = new HashMap<>();
	public static final Map<String, String> VARLIST_ATTRS = new HashMap<>();
	public static final Map<String, String> VARLISTENTRY_ATTRS = new HashMap<>();
	public static final Map<String, String> VARNAME_ATTRS = new HashMap<>();
	public static final Map<String, String> WARNING_ATTRS = new HashMap<>();
	
	public static final Map<String, String> ITEMIZEDLIST_LISTITEM_ATTRS = new HashMap<>();
	public static final Map<String, String> SIMPLELIST_LISTITEM_ATTRS = new HashMap<>();
	public static final Map<String, String> ORDEREDLIST_LISTITEM_ATTRS = new HashMap<>();
	public static final Map<String, String> VARLISTENTRY_LISTITEM_ATTRS = new HashMap<>();
	
	static {
		CHAPTER_TITLE_ATTRS.put("font-size", "18pt");
		
		SUBSCRIPT_ATTRS.put("font-size", "8pt");
		SUBSCRIPT_ATTRS.put("vertical-align", "sub");
		
		SUPERSCRIPT_ATTRS.put("font-size", "8pt");
		SUPERSCRIPT_ATTRS.put("vertical-align", "sub");
		
		SYNOPSIS_ATTRS.put("background-color", "#E0E0E0");
		SYNOPSIS_ATTRS.put("font-family", "monospace");
		SYNOPSIS_ATTRS.put("hyphenate", "false");
		SYNOPSIS_ATTRS.put("linefeed-treatment", "preserve");
		SYNOPSIS_ATTRS.put("space-after", "6pt");
		SYNOPSIS_ATTRS.put("space-before", "6pt");
		SYNOPSIS_ATTRS.put("text-align", "start");
		SYNOPSIS_ATTRS.put("white-space-collapse", "false");
		SYNOPSIS_ATTRS.put("white-space-treatment", "preserve");
		SYNOPSIS_ATTRS.put("wrap-option", "no-wrap");
		
		TERM_ATTRS.put("keep-together.within-column", "always");
		TERM_ATTRS.put("keep-with-next.within-column", "always");
		TERM_ATTRS.put("space-after", "6pt");
		TERM_ATTRS.put("space-before", "6pt");
		
		VARLIST_ATTRS.put("space-after", "6pt");
		VARLIST_ATTRS.put("space-before", "6pt");
		
		VARLISTENTRY_ATTRS.put("keep-together.within-column", "always");
		VARLISTENTRY_ATTRS.put("keep-with-next.within-column", "always");
		VARLISTENTRY_ATTRS.put("space-before", "6pt");
		
		VARLISTENTRY_LISTITEM_ATTRS.put("margin-left", "0.25in");
	}
	
	public final String outDir;

	public StandaloneFODocBookTagConverter(String inName, DocbookElement elem, ImageConverter imgConvert)
			throws IOException {
		super(inName, elem, imgConvert);
		this.outDir = "c:\\__work\\_sci\\";
	}

	public StandaloneFODocBookTagConverter(String in, ImageConverter imgConvert) throws IOException {
		super(in, imgConvert);
		this.outDir = "c:\\__work\\_sci\\";
	}

    public void createFOFile(final String fileName, final String contents) {
    	try (FileWriter writer = new FileWriter(outDir + fileName);) {
			writer.append(contents);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private String generateAttributeList(Map<String, String> attrs) {
		if (attrs == null || attrs.size() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		if (!attrs.isEmpty())
			sb.append(' ');
		for (Entry<String, String> e : attrs.entrySet()) {
			sb.append(e.getKey());
			sb.append("=\"");
			sb.append(e.getValue());
			sb.append("\" ");
		}
		return sb.toString();
	}
	
	private String generateXMLElement(String name, String content, Map<String, String> attrs, Map<String, String> extraAttrs) {
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		sb.append(name);
		sb.append(generateAttributeList(attrs));
		sb.append(generateAttributeList(extraAttrs));
		if (content == null || content.trim().equals(""))
			sb.append("/>");
		else {
			sb.append('>');
			sb.append(content);
			sb.append('<');
			sb.append(name);
			sb.append('>');
		}
		return sb.toString();
	}
	
	private String generateXMLElement(String name, String content, Map<String, String> attrs) {
		return generateXMLElement(name, content, attrs, null);
	}

	private String generateXMLElement(String name, Map<String, String> attrs) {
		return generateXMLElement(name, null, attrs);
	}

	private String escapeXMLContent(String content) {
		return content.replaceAll("&", "&amp;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
	}

	@Override
	public String generateImageCode(String code, String fileName, Map<String, String> attrs) {
		Map<String, String> outputAttributes = new HashMap<>();
		outputAttributes.put("src", "url(" + escapeXMLContent(fileName) + ")");
		return generateXMLElement(FO_EXTERNAL_GRAPHIC, outputAttributes);
	}

	@Override
	public String generateImageCode(Image img, String fileName, Map<String, String> attrs) {
		Map<String, String> outputAttributes = new HashMap<>();
		outputAttributes.put("src", "url(" + escapeXMLContent(fileName) + ")");
		return generateXMLElement(FO_EXTERNAL_GRAPHIC, outputAttributes);
	}

	@Override
	public String generateImageCode(String fileName, Map<String, String> attrs) {
		Map<String, String> outputAttributes = new HashMap<>();
		outputAttributes.put("src", "url(" + escapeXMLContent(fileName) + ")");
		return generateXMLElement(FO_EXTERNAL_GRAPHIC, outputAttributes);
	}

	@Override
	public String handleAnswer(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, null);
	}

	@Override
	public String handleBibliomixed(Map<String, String> attributes, String contents) throws SAXException {
		String id = attributes.get("id");
		return (id != null) ? generateXMLElement(FO_BASIC_LINK, contents, Collections.singletonMap("id", id))
				: generateXMLElement(FO_INLINE, contents, null);
	}

	@Override
	public String handleBibliomset(Map<String, String> attributes, String contents) throws SAXException {
		String id = attributes.get("id");
		return (id != null) ? generateXMLElement(FO_BASIC_LINK, contents, Collections.singletonMap("id", id))
				: generateXMLElement(FO_INLINE, contents, null);
	}

	@Override
	public String handleBook(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleCaption(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, CAPTION_ATTRS);
	}

	@Override
	public String handleCaution(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, CAUTION_ATTRS);
	}

	@Override
	public String handleChapter(Map<String, String> attributes, String contents) throws SAXException {
        return contents;
	}

	@Override
	public String handleCode(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, CODE_ATTRS);
	}

	@Override
	public String handleCommand(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, COMMAND_ATTRS);
	}

	@Override
	public String handleConstant(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, CONSTANT_ATTRS);
	}

	@Override
	public String handleEmphasis(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, EMPHASIS_ATTRS);
	}

	@Override
	public String handleFirstname(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, FIRSTNAME_ATTRS);
	}

	@Override
	public String handleFunction(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, FUNCTION_ATTRS);
	}

	@Override
	public String handleImagedata(Map<String, String> attributes, String contents) throws SAXException {
		Map<String, String> outputAttributes = new HashMap<>();
		outputAttributes.put("src", "url(" + escapeXMLContent(attributes.get("fileref")) + ")");
		return generateXMLElement(FO_EXTERNAL_GRAPHIC, outputAttributes);
	}

	@Override
	public String handleImageobject(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleImportant(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, IMPORTANT_ATTRS);
	}

	@Override
	public String handleInfo(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleInformalequation(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, INFORMALEQUATION_ATTRS);
	}

	@Override
	public String handleInformaltable(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String handleInlinemediaobject(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleItemizedlist(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleLatex(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handleLink(Map<String, String> attributes, String contents) throws SAXException {
		Map<String, String> outputAttributes = new HashMap<>();
		outputAttributes.put("internal-destination", escapeXMLContent(attributes.get("linkend")));
		return generateXMLElement(FO_BASIC_LINK, contents, outputAttributes);
	}

	@Override
	public String handleListitem(Map<String, String> attributes, String contents) throws SAXException {
		switch (getParentTagName()) {
		case "itemizedlist":
			generateXMLElement(FO_BLOCK, contents, ITEMIZEDLIST_LISTITEM_ATTRS);
			break;
		case "orderedlist":
			generateXMLElement(FO_BLOCK, contents, ORDEREDLIST_LISTITEM_ATTRS);
			break;
		case "simplelist":
			generateXMLElement(FO_BLOCK, contents, SIMPLELIST_LISTITEM_ATTRS);
			break;
		case "varlistentry":
			generateXMLElement(FO_BLOCK, contents, VARLISTENTRY_LISTITEM_ATTRS);
			break;
		default:
			break;
		}
		return "";
	}

	@Override
	public String handleLiteral(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, LITERAL_ATTRS);
	}

	@Override
	public String handleMediaobject(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleMember(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleNote(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, NOTE_ATTRS);
	}

	@Override
	public String handleOption(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, OPTION_ATTRS);
	}

	@Override
	public String handleOrderedlist(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handlePara(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, PARA_ATTRS);
	}

	@Override
	public String handlePart(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleProgramlisting(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, PROGRAMLISTING_ATTRS);
	}

	@Override
	public String handlePubdate(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, PUBDATE_ATTRS);
	}

	@Override
	public String handleQandaentry(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String handleQandaset(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String handleQuestion(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String handleRefentry(Map<String, String> attributes, String contents) throws SAXException {
		createFOFile("docs.fo", contents);
		return null;
	}

	@Override
	public String handleRefnamediv(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleRefname(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, REFNAME_ATTRS);
	}

	@Override
	public String handleRefpurpose(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, REFPURPOSE_ATTRS);
	}

	@Override
	public String handleRefsection(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleRefsynopsisdiv(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleReplaceable(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, REPLACEABLE_ATTRS);
	}

	@Override
	public String handleRevdescription(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleRevhistory(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return contents;
	}

	@Override
	public String handleRevision(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return contents;
	}

	@Override
	public String handleRevnumber(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, REVNUMBER_ATTRS);
	}

	@Override
	public String handleRevremark(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return contents;
	}

	@Override
	public String handleScreen(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, SCREEN_ATTRS);
	}

	@Override
	public String handleScreenshot(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handleSection(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleSimplelist(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleSubscript(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, SUBSCRIPT_ATTRS);
	}

	@Override
	public String handleSuperscript(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, SUPERSCRIPT_ATTRS);
	}

	@Override
	public String handleSurname(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, SURNAME_ATTRS);
	}

	@Override
	public String handleSynopsis(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, SYNOPSIS_ATTRS);
	}

	@Override
	public String handleTable(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handleTbody(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handleTd(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handleTerm(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, TERM_ATTRS);
	}

	@Override
	public String handleTh(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handleTip(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, TIP_ATTRS);
	}

	@Override
	public String handleTitle(Map<String, String> attributes, String contents) throws SAXException {
        switch(getParentTagName()) {
        case "chapter":
        	return generateXMLElement(FO_BLOCK, contents, CHAPTER_TITLE_ATTRS);
        case "part":
        	return generateXMLElement(FO_BLOCK, contents, PART_TITLE_ATTRS);
        case "info":
        	break;
        case "section":
        	return generateXMLElement(FO_BLOCK, contents, SECTION_TITLE_ATTRS);
        case "refsection":
        	return generateXMLElement(FO_BLOCK, contents, REFSECTION_TITLE_ATTRS);
        case "refsynopsisdiv":
        	return generateXMLElement(FO_BLOCK, contents, REFSYNOPSISDIV_TITLE_ATTRS);
        default:
        	break;
        }
        return null;
	}

	@Override
	public String handleTr(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handleUlink(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handleVariablelist(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleVarlistentry(Map<String, String> attributes, String contents) throws SAXException {
		return contents;
	}

	@Override
	public String handleVarname(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_INLINE, contents, VARNAME_ATTRS);
	}

	@Override
	public String handleWarning(Map<String, String> attributes, String contents) throws SAXException {
		return generateXMLElement(FO_BLOCK, contents, WARNING_ATTRS);
	}

	@Override
	public String handleXref(Map<String, String> attributes, String contents) throws SAXException {
		// TODO Auto-generated method stub
		return null;
	}
}
