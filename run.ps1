$ErrorActionPreference = "Stop"

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$lib = Join-Path $root "APPjeauge_lib\*"
$out = Join-Path $root "out"

if (-not (Test-Path -LiteralPath $out)) {
  & (Join-Path $root "compile.ps1")
}

Set-Location -LiteralPath $root
java -cp "$out;$root;$lib" shell.sijoumi.etatcuve.Cuve

