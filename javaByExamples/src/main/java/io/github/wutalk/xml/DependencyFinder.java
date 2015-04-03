/*
 * @(#)	2015-2-25
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.xml;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class Dependency {
	private String groupId = "";
	private String artifactId = "";
	private String version = "";
	private String scope = "";

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "Dependency [groupId=" + groupId + ", artifactId=" + artifactId + ", version="
				+ version + ", scope=" + scope + "]";
	}

}

/**
 * 
 * @author wutalk
 */
public class DependencyFinder {

	Logger logger = Logger.getLogger(DependencyFinder.class);
	DocumentBuilder builder;
	Map<String, Dependency> dependenciesMap = new TreeMap<String, Dependency>();
	static Set<String> skipDirs = new HashSet<String>();
	static {
		skipDirs.add("bin");
		skipDirs.add("src");
		skipDirs.add("target");
		skipDirs.add("installation");
		skipDirs.add("robot");
		skipDirs.add("smoke");
		skipDirs.add("smoke_local");
		skipDirs.add("simulator");
	}

	public DependencyFinder() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// if (args.length == 0) {
		// System.out.println("Usage: java -jar DependencyFinder.jar path debugLevel");
		// System.exit(0);
		// }

		DependencyFinder finder = new DependencyFinder();

		String fileName = "/your-project/pom.xml";

		// finder.parsePom(fileName);

		String dir = "/your-project/trunk/";
		String dir2 = "/your-project/b";
		String dir3 = "/your-project/c";

		finder.findPoms(new File(dir2));
		finder.findPoms(new File(dir3));
		finder.format();
	}

	/**
	 * 
	 */
	private void format() {
		logger.info("total depies size: " + dependenciesMap.size());

		Set<Entry<String, Dependency>> entrySet = dependenciesMap.entrySet();
		Iterator<Entry<String, Dependency>> iter = entrySet.iterator();
		while (iter.hasNext()) {
			Entry<String, Dependency> e = iter.next();
			Dependency dep = e.getValue();
			// filter
			String key = e.getKey();
			String scope = dep.getScope();
			String version = dep.getVersion();
			if (scope.equals("test") || scope.equals("provided")) {
				logger.info("remove " + scope + " scope: " + key);
				iter.remove();
				continue;
			} else if (version.equals("${rpmVersion}") || version.equals("${nbi-common-version}")) {
				// ${nbi-common-version} or ${rpmVersion} ...
				logger.info("remove " + version + " version: " + key);
				iter.remove();
				continue;
			}
		}

		logger.info("after filter depies size: " + dependenciesMap.size());
		entrySet = dependenciesMap.entrySet();
		for (Entry<String, Dependency> e : entrySet) {
			Dependency dep = e.getValue();
			String version = dep.getVersion();
			System.out.println(dep.getArtifactId() + ":" + version + "," + version);
		}
	}

	private void findPoms(File f) {
		logger.debug("+++++++++++++start find poms++++++++++++++");
		if ("pom.xml".equals(f.getName())) {
			parsePom(f);
		} else if (f.isDirectory()) {
			File[] files = f.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.startsWith(".") || skipDirs.contains(name)) {
						return false;
					}
					return true;
				}
			});
			for (File file : files) {
				logger.debug("found file: " + file.getName());
				findPoms(file);
			}
		}
	}

	private void parsePom(File pomFile) {
		logger.info("\n============start parse pom " + pomFile.getPath());
		Document doc = null;
		try {
			doc = builder.parse(pomFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList dependencies = doc.getElementsByTagName("dependency");

		int size = dependencies.getLength();
		logger.info("original size: " + size);

		// key = "artifactId-version"
		Map<String, Dependency> depies = new HashMap<String, Dependency>();

		// Set<String> tags = new TreeSet<String>();
		for (int i = 0; i < size; i++) {
			Node dependency = dependencies.item(i);
			NodeList childNodes = dependency.getChildNodes();
			int attrLength = childNodes.getLength();

			Dependency dep = new Dependency();
			for (int j = 0; j < attrLength; j++) {
				Node item = childNodes.item(j);

				String nodeName = item.getNodeName();
				String nvalue = item.getTextContent().trim();
				// logger.debug("nodeName=" + nodeName + ", nvalue=" + nvalue);

				if ("groupId".equals(nodeName)) {
					dep.setGroupId(nvalue);
				} else if ("artifactId".equals(nodeName)) {
					dep.setArtifactId(nvalue);
				} else if ("scope".equals(nodeName)) {
					dep.setScope(nvalue);
				} else if ("version".equals(nodeName)) {
					dep.setVersion(nvalue);
				}
			}
			String key = dep.getArtifactId() + dep.getVersion();
			// update item
			updateDep(dep, key);

			depies.put(key, dep);
		}
		logger.info("got " + depies.size() + " depies: " + depies.keySet());
		dependenciesMap.putAll(depies);
	}

	private void updateDep(Dependency dep, String key) {
		Dependency existDep = dependenciesMap.get(key);
		if (existDep != null) {
			if (dep.getScope().length() == 0 && existDep.getScope().length() != 0) {
				dep.setScope(existDep.getScope());
			}
			if (dep.getVersion().length() == 0 && existDep.getVersion().length() != 0) {
				dep.setVersion(existDep.getVersion());
			}
		}
	}
}
