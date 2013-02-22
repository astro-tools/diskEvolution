function writeHeader(relURL) {
    githubURL = "https://github.com/astro-tools/diskEvolution";
    document.write("<header>");
    writeTitle(relURL);
    writeViewProject(githubURL);
    writeGetCode(githubURL);
    writeNavigationLinks(relURL);
    document.write("</header>");
}

function writeTitle(relURL) {
    document.write("  <h1>");
    document.write("    <a href='" + relURL + "index.html'>");
    document.write("      diskEvolution");
    document.write("    </a>");
    document.write("  </h1>");
}

function writeViewProject(githubURL) {
    document.write("  <p class='view'>");
    document.write("    <a href='" + githubURL + "'>");
    document.write("      View the Project on GitHub");
    document.write("      <small>diskEvolution</small>");
    document.write("    </a>");
    document.write("  </p>");
}

function writeGetCode(githubURL) {
    document.write("  <ul class='getCode'>");
    document.write("    <li>");
    document.write("      <a href='" + githubURL + "/zipball/master'>");
    document.write("        Download <strong>ZIP File</strong>");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + githubURL + "/tarball/master'>");
    document.write("        Download <strong>TAR Ball</strong>");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + githubURL + "'>");
    document.write("        View On <strong>GitHub</strong>");
    document.write("      </a>");
    document.write("    </li>");
    document.write("  </ul>");
}

function writeNavigationLinks(relURL) {
    shumwayURL = "http://shumway.physics.asu.edu";
    rtfdURL = "http://diskEvolution.readthedocs.org/en/latest/";
    listURL = "https://groups.google.com/forum/?fromgroups#!forum/diskEvolution";
    document.write("  <ul>");
    document.write("    <li>");
    document.write("      <a href='" + rtfdURL + "'>");
    document.write("        Documentation");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + rtfdURL + "features.html'>");
    document.write("        Features");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + rtfdURL + "building.html'>");
    document.write("        Build / Install");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + rtfdURL + "faq.html'>");
    document.write("        FAQ");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + listURL + "'>");
    document.write("        Discussion Forum");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + githubURL + "/issues'>");
    document.write("        Bug Tracker");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + relURL + "team/people.html'>");
    document.write("        People");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + relURL + "team/groups.html'>");
    document.write("        Groups Using diskEvolution");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + rtfdURL + "publications.html'>");
    document.write("        Publications Using diskEvolution");
    document.write("      </a>");
    document.write("    </li>");
    document.write("    <li>");
    document.write("      <a href='" + relURL + "team/funding.html'>");
    document.write("        Sponsors and Funding");
    document.write("      </a>");
    document.write("    </li>");
    document.write("  </ul>");
}

function writeFooter(relURL) {
    shumwayGitHubURL = "https://github.com/shumway";
    document.write("  <footer>");
    document.write("    <p>This project is maintained by");
    document.write("      <a href='" + shumwayGitHubURL + "'>shumway</a>");
    document.write("    </p>");
    document.write("    <p><small>");
    document.write("      Hosted by GitHub Pages &mdash;");
    document.write("      Theme by");
    document.write("      <a href='https://github.com/orderedlist'>");
    document.write("        orderedList</a>");
    document.write("    </small></p>"); 
    document.write("  </footer>");
}

