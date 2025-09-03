val compilerPlugins = List(
  compilerPlugin("org.polyvariant" % "better-tostring" % "0.3.17" cross CrossVersion.full)
)

val commonSetting = Seq(
  scalaVersion := "3.7.2",
  fork in Test := true,
  libraryDependencies ++= compilerPlugins,
  scalacOptions -= "-Xfatal-warnings"
)


lazy val root = (project in file("."))
  .settings(
    name := "scala3Feature"
  )
  .settings(commonSetting)
  .settings(skip in publish := true)

