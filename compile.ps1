$ErrorActionPreference = "Stop"

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$lib = Join-Path $root "APPjeauge_lib\*"
$out = Join-Path $root "out"

if (Test-Path -LiteralPath $out) {
  Remove-Item -LiteralPath $out -Recurse -Force
}
New-Item -ItemType Directory -Force -Path $out | Out-Null

$files = Get-ChildItem -LiteralPath $root -Recurse -Filter "*.java" |
  Where-Object { $_.FullName -notlike "$out*" } |
  ForEach-Object { $_.FullName }

javac -encoding UTF-8 -cp ".;$lib" -d $out $files

