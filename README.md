# recipeProvider

사용자가 가진 재료에 따라 레시피를 추천해주는 레시피북

## Contents

1. [What is this](#what-is-this)
2. [Functions](#Functions)
3. [additional plans?](#additional-plans)

## What is this

일반적인 레시피 앱과 다르게, 사용자가 가진 재료에 따라 당장 만들 수 있는 최적의 레시피를 제공하는 앱입니다.
집에 있는 요리 재료를 앱에 입력하면, 해당 재료들만을 이용해서 만들 수 있는 음식을 나열합니다.
레시피와 재료의 종류는 사용자 재량에 따라 추가하거나 삭제할 수 있습니다.

## Functions

데이터베이스
  - 기본적으로 제공되는 재료, 레시피 리스트
  - 사용자의 입력에 따른 재료 잔량 설정
  - 사용자 임의로 재료, 레시피 리스트 추가 가능

앱
  - 재료 잔량을 데이터베이스에 입력
  - 재료 잔량을 포함하는 레시피를 검색하여 출력

## additional plans?

이것만 더 추가하면 해당 레시피를 사용 가능하다 - 소량의 재료가 부족해서 할 수 없는 레시피 출력?
레시피에 따른 재료 검색? - 레시피를 검색해서 부족한 재료가 무었인지 확인 가능
