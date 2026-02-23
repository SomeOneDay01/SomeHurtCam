# Changelog

## v1.0.1 (2026-02-22)

### Added
- Added full support for changing the HurtCam toggle key in the `Controls` menu.
- Added a dedicated key binding category: `SomeHurtCam`.

### Changed
- Switched key registration to the standard Fabric API (`KeyBindingHelper`) for proper integration with in-game controls.
- Replaced the previous non-standard registration approach via `GameOptions.keysAll`.

### Removed
- Removed the `GameOptionsAccessor` mixin and its entry from `somehurtcam.mixins.json`.

### Technical
- Added `fabric-api` dependency in `build.gradle`.
