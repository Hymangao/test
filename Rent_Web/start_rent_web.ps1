$ErrorActionPreference = "Stop"

$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$appDir = Join-Path $scriptDir "property_manager"

$pythonCandidates = @(
    (Join-Path $scriptDir ".venv\Scripts\python.exe"),
    (Join-Path (Split-Path $scriptDir -Parent) ".venv\Scripts\python.exe")
)

$pythonExe = $null
foreach ($candidate in $pythonCandidates) {
    if (Test-Path $candidate) {
        $pythonExe = $candidate
        break
    }
}

if (-not $pythonExe) {
    Write-Host "Python virtual environment not found." -ForegroundColor Red
    Write-Host "Expected one of:" -ForegroundColor Yellow
    $pythonCandidates | ForEach-Object { Write-Host "  $_" }
    exit 1
}

if (-not (Test-Path $appDir)) {
    Write-Host "App directory not found: $appDir" -ForegroundColor Red
    exit 1
}

Set-Location $appDir

$requirementsFile = Join-Path $appDir "requirements.txt"
if (Test-Path $requirementsFile) {
    & $pythonExe -m pip install -r $requirementsFile | Out-Null
}

Write-Host "Starting Rent Web..." -ForegroundColor Green
& $pythonExe "app.py"